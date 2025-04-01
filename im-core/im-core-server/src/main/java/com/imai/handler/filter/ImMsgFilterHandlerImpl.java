package com.imai.handler.filter;

import com.imai.core.domain.bo.ImConversationMemberBo;
import com.imai.core.domain.bo.ImMessageBo;
import com.imai.core.domain.vo.ImConversationMemberVo;
import com.imai.core.domain.vo.ImConversationVo;
import com.imai.core.service.IImConversationMemberService;
import com.imai.core.service.IImConversationService;
import com.imai.handler.ImSendMsg;
import com.imai.handler.store.ImStoreHandler;
import com.imai.ws.ContentItem;
import com.imai.ws.ImResponse;
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
import java.util.stream.Collectors;

/**
 * 过滤器,拦截消息,例如群聊判断用户是否在群内
 * ImMsgFilterHandlerImpl 实现了 ImMsgFilterHandler 接口，用于过滤即时消息。
 * 该类使用了 Dubbo 服务注解，表示它是一个 Dubbo 服务提供者。
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
    @Resource
    private IImConversationService imConversationService;

    /**
     * 过滤消息
     *
     * @param message    消息内容，JSON 格式的字符串
     * @param fromUserId 发送消息的用户 ID
     * @param channelId  通道 ID，例如 WebSocket 连接的 ID
     * @return 过滤结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public FilterResult filter(String message, Long fromUserId, String channelId) {
        log.info("[filter] start - message:{}, userId:{}", message, fromUserId);

        // 1. 解析消息内容
        WebSocketMessage webSocketMessage = JsonUtils.parseObject(message, WebSocketMessage.class);
        if (webSocketMessage == null) {
            log.error("[filter] message is null");
            sendErrorResponse(fromUserId, ImResponseCode.MESSAGE_FORMAT_ERROR);
            return FilterResult.error(ImResponseCode.MESSAGE_FORMAT_ERROR.getDescChinese(), ImResponseCode.MESSAGE_FORMAT_ERROR.getCode());
        }

        // 2. 检查消息命令类型
        if (webSocketMessage.getCmd() == null) {
            log.error("[filter] cmd is null");
            sendErrorResponse(fromUserId, ImResponseCode.MESSAGE_INCOMPLETE);
            return FilterResult.error(ImResponseCode.MESSAGE_INCOMPLETE.getDescChinese(), ImResponseCode.MESSAGE_INCOMPLETE.getCode());
        }
        int cmd = webSocketMessage.getCmd();

        // 检查会话ID是否存在
        if (webSocketMessage.getRoute() == null || webSocketMessage.getRoute().getConversationId() == null) {
            log.error("[filter] conversationId is null");
            sendErrorResponse(fromUserId, ImResponseCode.MESSAGE_INCOMPLETE);
            return FilterResult.error(ImResponseCode.MESSAGE_INCOMPLETE.getDescChinese(), ImResponseCode.MESSAGE_INCOMPLETE.getCode());
        }

        // 3. 获取会话信息
        ImConversationVo conversationVo = imConversationService.queryById(webSocketMessage.getRoute().getConversationId());
        if (conversationVo == null) {
            log.error("[filter] conversation not found, conversationId:{}", webSocketMessage.getRoute().getConversationId());
            sendErrorResponse(fromUserId, ImResponseCode.NOT_CONVERSATION);
            return FilterResult.error(ImResponseCode.NOT_CONVERSATION.getDescChinese(), ImResponseCode.NOT_CONVERSATION.getCode());
        }

        // 4. 针对不同会话类型进行处理
        // 4.1 陌生人单聊
        if (conversationVo.getConversationType() == ConversationType.STRANGER_CHAT.getCode() && cmd == CmdType.STRANGER_CHAT.getCode()) {
            log.info("[filter] 处理陌生人单聊消息, conversationId:{}, fromUserId:{}", conversationVo.getId(), fromUserId);
            return strangerChat(fromUserId, webSocketMessage, conversationVo);
        }

        // 4.2 群聊
        if (conversationVo.getConversationType() == ConversationType.GROUP.getCode() && cmd == CmdType.GROUP_CHAT.getCode()) {
            log.info("[filter] 处理群聊消息, conversationId:{}, fromUserId:{}", conversationVo.getId(), fromUserId);
            return groupChat(fromUserId, webSocketMessage, conversationVo);
        }

        // 4.3 单聊
        if (conversationVo.getConversationType() == ConversationType.SINGLE.getCode() && cmd == CmdType.SINGLE_CHAT.getCode()) {
            log.info("[filter] 处理单聊消息, conversationId:{}, fromUserId:{}", conversationVo.getId(), fromUserId);
            return strangerChat(fromUserId, webSocketMessage, conversationVo);
        }

        // 5. 其他类型的消息或命令，记录日志并拦截
        log.warn("[filter] 不支持的消息类型, cmd:{}, conversationType:{}", cmd, conversationVo.getConversationType());
        sendErrorResponse(fromUserId, ImResponseCode.UNSUPPORTED_MESSAGE_TYPE);
        return FilterResult.error(ImResponseCode.UNSUPPORTED_MESSAGE_TYPE.getDescChinese(), ImResponseCode.UNSUPPORTED_MESSAGE_TYPE.getCode());
    }

    /**
     * 陌生人单聊
     *
     * @param fromUserId       用户id
     * @param webSocketMessage 消息
     * @return 过滤结果
     */
    private FilterResult strangerChat(Long fromUserId, WebSocketMessage webSocketMessage, ImConversationVo imConversationVo) {
        Long conversationId = webSocketMessage.getRoute().getConversationId();
        ImConversationMemberBo imConversationMemberBo = new ImConversationMemberBo();
        imConversationMemberBo.setFkConversationId(conversationId);

        // 使用Stream API简化集合操作
        List<Long> toList = conversationMemberService.queryList(imConversationMemberBo)
            .stream()
            .map(ImConversationMemberVo::getFkUserId)
            .collect(Collectors.toList());

        // 判断from是否在conversationId中
        if (!toList.contains(fromUserId)) {
            sendErrorResponse(fromUserId, ImResponseCode.SENDER_NOT_IN_CONVERSATION);
            return FilterResult.error(ImResponseCode.SENDER_NOT_IN_CONVERSATION.getDescChinese(), ImResponseCode.SENDER_NOT_IN_CONVERSATION.getCode());
        } else {
            toList.remove(fromUserId);
        }

        // 提取获取接收者逻辑
        Long to = getReceiverId(fromUserId, toList);
        if (to == null) {
            sendErrorResponse(fromUserId, ImResponseCode.RECEIVER_NOT_IN_CONVERSATION);
            return FilterResult.error(ImResponseCode.RECEIVER_NOT_IN_CONVERSATION.getDescChinese(), ImResponseCode.RECEIVER_NOT_IN_CONVERSATION.getCode());
        }

        // 检查必要的字段
        if (webSocketMessage.getHeader() == null || webSocketMessage.getRoute() == null || webSocketMessage.getContent() == null) {
            sendErrorResponse(fromUserId, ImResponseCode.MESSAGE_INCOMPLETE);
            return FilterResult.error(ImResponseCode.MESSAGE_INCOMPLETE.getDescChinese(), ImResponseCode.MESSAGE_INCOMPLETE.getCode());
        }

        // 保存消息表
        ImMessageBo messageBo = buildMessageBo(fromUserId, webSocketMessage, imConversationVo);

        List<Long> receiverIds = new ArrayList<>();
        receiverIds.add(to);
        receiverIds.add(fromUserId);

        boolean store = imStoreHandler.store(messageBo, webSocketMessage, receiverIds);
        if (!store) {
            log.error("[filter] 保存消息失败 message:{}", webSocketMessage);
            sendErrorResponse(fromUserId, ImResponseCode.MESSAGE_STORE_FAILED);
            return FilterResult.error(ImResponseCode.MESSAGE_STORE_FAILED.getDescChinese(), ImResponseCode.MESSAGE_STORE_FAILED.getCode());
        }
        return FilterResult.success();
    }

    /**
     * 提取获取接收者逻辑
     */
    private Long getReceiverId(Long fromUserId, List<Long> toList) {
        if (!toList.iterator().hasNext()) {
            sendErrorResponse(fromUserId, ImResponseCode.RECEIVER_NOT_IN_CONVERSATION);
            return null;
        }
        return toList.iterator().next();
    }

    /**
     * 提取构建消息对象逻辑
     */
    private ImMessageBo buildMessageBo(Long fromUserId, WebSocketMessage webSocketMessage, ImConversationVo imConversationVo) {
        ImMessageBo messageBo = new ImMessageBo();
        messageBo.setFkConversationId(webSocketMessage.getRoute().getConversationId());
        messageBo.setFkFromUserId(fromUserId);
        messageBo.setMsgStatus(1L);
        messageBo.setCmd(Long.valueOf(webSocketMessage.getCmd()));
        messageBo.setPersistent(1L);
        messageBo.setPriority(1L);
        messageBo.setLocalMsgId(webSocketMessage.getHeader().getLocalId());
        messageBo.setMsgType(webSocketMessage.getRoute().getType() != null ?
            Long.valueOf(webSocketMessage.getRoute().getType()) : null);
        messageBo.setMsgText(webSocketMessage.getContent().getText() != null ? webSocketMessage.getContent().getText() : "");
        messageBo.setConversationType(imConversationVo.getConversationType());

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
        return messageBo;
    }

    /**
     * 发送错误响应
     *
     * @param userId       用户id
     * @param responseCode 响应码
     */
    private void sendErrorResponse(Long userId, ImResponseCode responseCode) {
        WebSocketMessage errorResponse = new WebSocketMessage();
        errorResponse.setDirection(MessageDirection.RESPONSE.getCode());
        // 初始化response对象
        ImResponse response = new ImResponse();
        response.setCode(responseCode.getCode());
        response.setMessage(responseCode.getDescChinese());
        errorResponse.setResponse(response);

        try {
            imSendMsg.sendMsgToUser(JsonUtils.toJsonString(errorResponse), userId);
        } catch (Exception e) {
            log.error("[filter] 发送错误响应失败", e);
        }
    }

    /**
     * 群聊
     *
     * @param fromUserId       用户id
     * @param webSocketMessage 消息
     * @param imConversationVo 会话信息
     * @return 过滤结果
     */
    private FilterResult groupChat(Long fromUserId, WebSocketMessage webSocketMessage, ImConversationVo imConversationVo) {
        Long conversationId = webSocketMessage.getRoute().getConversationId();
        ImConversationMemberBo imConversationMemberBo = new ImConversationMemberBo();
        imConversationMemberBo.setFkConversationId(conversationId);

        // 使用Stream API简化集合操作
        List<Long> toList = conversationMemberService.queryList(imConversationMemberBo)
            .stream()
            .map(ImConversationMemberVo::getFkUserId)
            .collect(Collectors.toList());

        // 判断from是否在conversationId中
        if (!toList.contains(fromUserId)) {
            sendErrorResponse(fromUserId, ImResponseCode.SENDER_NOT_IN_CONVERSATION);
            return FilterResult.error(ImResponseCode.SENDER_NOT_IN_CONVERSATION.getDescChinese(), ImResponseCode.SENDER_NOT_IN_CONVERSATION.getCode());
        }

        // 获取发送者的会话成员信息
        ImConversationMemberBo senderMemberBo = new ImConversationMemberBo();
        senderMemberBo.setFkConversationId(conversationId);
        senderMemberBo.setFkUserId(fromUserId);
        List<ImConversationMemberVo> senderMemberList = conversationMemberService.queryList(senderMemberBo);

        if (!senderMemberList.isEmpty()) {
            ImConversationMemberVo senderMember = senderMemberList.get(0);
            // 检查用户是否被禁言
            if (senderMember.getMuteEndTime() != null && senderMember.getMuteEndTime().getTime() > System.currentTimeMillis()) {
                sendErrorResponse(fromUserId, ImResponseCode.USER_MUTED);
                return FilterResult.error(ImResponseCode.USER_MUTED.getDescChinese(), ImResponseCode.USER_MUTED.getCode());
            }
        }

        // 检查群组状态
        if (imConversationVo.getConversationStatus() != null && imConversationVo.getConversationStatus() != 1) {
            sendErrorResponse(fromUserId, ImResponseCode.GROUP_DISSOLVED);
            return FilterResult.error(ImResponseCode.GROUP_DISSOLVED.getDescChinese(), ImResponseCode.GROUP_DISSOLVED.getCode());
        }

        // 检查必要的字段
        if (webSocketMessage.getHeader() == null || webSocketMessage.getRoute() == null || webSocketMessage.getContent() == null) {
            sendErrorResponse(fromUserId, ImResponseCode.MESSAGE_INCOMPLETE);
            return FilterResult.error(ImResponseCode.MESSAGE_INCOMPLETE.getDescChinese(), ImResponseCode.MESSAGE_INCOMPLETE.getCode());
        }

        // 保存消息表
        ImMessageBo messageBo = buildMessageBo(fromUserId, webSocketMessage, imConversationVo);

        // 处理消息存储和分发
        try {
            // 群聊消息需要发送给所有群成员（包括发送者自己）
            boolean store = imStoreHandler.store(messageBo, webSocketMessage, toList);
            if (!store) {
                log.error("[filter] 保存消息失败 message:{}", webSocketMessage);
                sendErrorResponse(fromUserId, ImResponseCode.MESSAGE_STORE_FAILED);
                return FilterResult.error(ImResponseCode.MESSAGE_STORE_FAILED.getDescChinese(), ImResponseCode.MESSAGE_STORE_FAILED.getCode());
            }
            return FilterResult.success();
        } catch (Exception e) {
            log.error("[filter] 群聊消息处理失败", e);
            sendErrorResponse(fromUserId, ImResponseCode.MESSAGE_PROCESS_FAILED);
            return FilterResult.error(ImResponseCode.MESSAGE_PROCESS_FAILED.getDescChinese(), ImResponseCode.MESSAGE_PROCESS_FAILED.getCode());
        }
    }
}
