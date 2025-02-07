package com.imai.handler.filter;

import com.imai.core.domain.bo.ImMessageBo;
import com.imai.core.service.IImConversationMemberService;
import com.imai.handler.ImSendMsg;
import com.imai.handler.store.ImStoreHandler;
import com.imai.ws.ContentItem;
import com.imai.ws.Mentions;
import com.imai.ws.Quote;
import com.imai.ws.WebSocketMessage;
import com.imai.ws.enums.CmdType;
import com.imai.ws.enums.ConversationType;
import com.imai.ws.enums.ImResponseCode;
import com.imai.ws.enums.MessageDirection;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.common.json.utils.JsonUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器,拦截消息,例如群聊判断用户是否在群内,
 */
@DubboService
@Slf4j
public class ImMsgFilterHandlerImpl implements ImMsgFilterHandler {

    @DubboReference
    private ImSendMsg imSendMsg;
    @Resource
    private ImStoreHandler imStoreHandler;
    @Resource
    private IImConversationMemberService conversationMemberService;

    /**
     * 过滤消息
     *
     * @param message    消息
     * @param fromUserId 用户id
     * @param channelId  通道id
     * @return 是否过滤
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean filter(String message, Long fromUserId, String channelId) {
        log.info("[filter] start - message:{}, userId:{}", message, fromUserId);

        // 处理cmd
        WebSocketMessage webSocketMessage = JsonUtils.parseObject(message, WebSocketMessage.class);
        if (webSocketMessage == null) {
            log.error("[filter] message is null");
            return false;
        }

        if (webSocketMessage.getCmd() == null) {
            log.error("[filter] cmd is null");
            return false;
        }
        int cmd = webSocketMessage.getCmd();

        // 陌生人单聊
        if (cmd == CmdType.STRANGER_CHAT.getCode()) {
            if (strangerChat(fromUserId, webSocketMessage)) {
                return false;
            }
        }

        if (cmd == CmdType.GROUP_CHAT.getCode()) {
            // 群聊
        }

        // 处理content

        // 要先判断会话类型

        try {
            boolean result = imSendMsg.sendMsgToUser("RPC: filterRes:" + message, fromUserId);
            log.info("[filter] end - result:{}", result);
            return result;
        } catch (Exception e) {
            log.error("[filter] error", e);
            return false;
        }
    }

    /**
     * 陌生人单聊
     *
     * @param fromUserId       用户id
     * @param webSocketMessage 消息
     * @return 是否过滤
     */
    private boolean strangerChat(Long fromUserId, WebSocketMessage webSocketMessage) {
        Long conversationId = webSocketMessage.getRoute().getConversationId();
        Long to = webSocketMessage.getRoute().getTo();
        webSocketMessage.getRoute().setFrom(fromUserId);

        // 判断to是否在conversationId中
        boolean isInConversation = conversationMemberService.contains(conversationId, to);
        if (!isInConversation) {
            sendErrorResponse(fromUserId, ImResponseCode.RECEIVER_NOT_IN_CONVERSATION);
            return false;
        }

        // 判断from是否在conversationId中
        boolean isFromInConversation = conversationMemberService.contains(conversationId, fromUserId);
        if (!isFromInConversation) {
            sendErrorResponse(fromUserId, ImResponseCode.SENDER_NOT_IN_CONVERSATION);
            return false;
        }

        // 检查必要的字段
        if (webSocketMessage.getHeader() == null || webSocketMessage.getRoute() == null || webSocketMessage.getContent() == null) {
            throw new RuntimeException("消息头、路由信息或消息内容不能为空");
        }
        // 保存消息表
        ImMessageBo messageBo = new ImMessageBo();

        // 设置基础信息
        messageBo.setFkConversationId(webSocketMessage.getRoute().getConversationId());
        messageBo.setFkFromUserId(fromUserId);
        messageBo.setToUid(webSocketMessage.getRoute().getTo());

        // 设置消息状态和命令
        messageBo.setMsgStatus(1L); // 初始状态
        messageBo.setCmd(Long.valueOf(webSocketMessage.getCmd()));
        messageBo.setPersistent(1L);
        messageBo.setPriority(1L);

        // 从 Header 获取信息
        messageBo.setLocalMsgId(webSocketMessage.getHeader().getLocalId());

        // 从 Route 获取信息
        messageBo.setMsgType(webSocketMessage.getRoute().getType() != null ?
            Long.valueOf(webSocketMessage.getRoute().getType()) : null);
        messageBo.setConversationType(webSocketMessage.getRoute().getConversationType() != null ?
            Long.valueOf(webSocketMessage.getRoute().getConversationType()) : ConversationType.SINGLE.getCode());

        // 从 Content 获取信息
        String text = webSocketMessage.getContent().getText();
        messageBo.setMsgText(text != null ? text : "");

        // 处理 Content 中的 mentions
        if (webSocketMessage.getContent().getMentions() != null) {
            Mentions mentions = webSocketMessage.getContent().getMentions();
            // 设置@全体成员标记
            messageBo.setAtAll(mentions.getAll() != null && mentions.getAll() ? 1L : 0L);
            // 设置@用户列表
            messageBo.setAtUsers(JsonUtils.toJsonString(mentions));
        } else {
            messageBo.setAtUsers("[]");
            messageBo.setAtAll(0L);
        }

        // 处理引用消息
        if (webSocketMessage.getContent().getQuote() != null) {
            Quote quote = webSocketMessage.getContent().getQuote();
            messageBo.setRefType(1L); // 引用消息
            messageBo.setRootMsgId(0L); // 暂时设为0，需要根据实际业务查找原始消息
            messageBo.setParentMsgId(quote.getMsgId() != null ? Long.parseLong(quote.getMsgId()) : 0L);
        } else {
            messageBo.setRefType(0L); // 原创消息
            messageBo.setRootMsgId(0L);
            messageBo.setParentMsgId(0L);
        }

        // 处理扩展字段
        messageBo.setExtras(webSocketMessage.getContent().getExtension() != null ?
            webSocketMessage.getContent().getExtension() : "{}");

        // 处理富文本内容
        if (webSocketMessage.getContent().getItems() != null && !webSocketMessage.getContent().getItems().isEmpty()) {
            messageBo.setPayload(JsonUtils.toJsonString(webSocketMessage.getContent().getItems()));
            // 检查是否有媒体类型的内容
            for (ContentItem item : webSocketMessage.getContent().getItems()) {
                if (item.getUrl() != null) {
                    messageBo.setMediaUrl(item.getUrl());
                    break;
                }
            }
        } else {
            messageBo.setPayload("[]");
            messageBo.setMediaUrl("");
        }

        // 处理接收者信息
        if (webSocketMessage.getRoute().getTarget() != null && !webSocketMessage.getRoute().getTarget().isEmpty()) {
            messageBo.setReceiverOnly(String.join(",",
                webSocketMessage.getRoute().getTarget().stream()
                    .map(String::valueOf)
                    .toList()));
            messageBo.setReceiverCount((long) webSocketMessage.getRoute().getTarget().size());
        } else {
            messageBo.setReceiverOnly("");
            messageBo.setReceiverCount(1L);
        }

        // 设置其他默认值
        messageBo.setRefCount(0L); // 被引用次数
        messageBo.setDeleted(0L); // 未删除
        messageBo.setNeedReceipt(1L); // 需要回执

        List<Long> receiverIds = new ArrayList<>();
        receiverIds.add(webSocketMessage.getRoute().getTo());
        receiverIds.add(webSocketMessage.getRoute().getFrom());

        boolean store = imStoreHandler.store(messageBo, webSocketMessage, receiverIds);
        if (!store) {
            log.error("[filter] 保存消息失败 message:{}", webSocketMessage);
            throw new RuntimeException("保存消息失败");
        }
        return true;
    }

    /**
     * 发送错误响应
     *
     * @param userId       用户id
     * @param responseCode 响应码
     */
    private void sendErrorResponse(Long userId, ImResponseCode responseCode) {
        WebSocketMessage errorResponse = WebSocketMessage.builder()
            .direction(MessageDirection.RESPONSE.getCode())
            .code(responseCode.getCode())
            .message(responseCode.getDescChinese())
            .build();

        try {
            imSendMsg.sendMsgToUser(JsonUtils.toJsonString(errorResponse), userId);
        } catch (Exception e) {
            log.error("[filter] 发送错误响应失败", e);
        }
    }
}
