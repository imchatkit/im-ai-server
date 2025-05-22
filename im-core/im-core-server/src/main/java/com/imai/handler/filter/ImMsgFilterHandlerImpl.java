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
import com.imai.ws.enums.ConversationTypeEnum;
import com.imai.ws.enums.ImResponseCodeEnum;
import com.imai.ws.enums.MessageDirectionEnum;
import com.imai.ws.enums.RequestCmdEnum;
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
 * 即时消息过滤处理器
 * <p>
 * 负责对消息进行验证和过滤，确保消息的合法性和有效性。
 * 主要功能包括：
 * 1. 验证消息格式和必要字段
 * 2. 检查发送者和接收者的会话关系
 * 3. 根据会话类型（单聊、群聊、陌生人聊天）进行不同处理
 * 4. 处理消息存储和分发
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
        log.info("[filter] 开始处理消息 - message:{}, userId:{}", message, fromUserId);

        // 1. 解析消息内容
        WebSocketMessage webSocketMessage = parseMessage(message);
        if (webSocketMessage == null) {
            return createErrorResult(fromUserId, ImResponseCodeEnum.MESSAGE_FORMAT_ERROR);
        }

        // 2. 验证消息基本结构
        FilterResult validationResult = validateMessageStructure(webSocketMessage, fromUserId);
        if (!validationResult.isSuccess()) {
            return validationResult;
        }

        // 3. 获取会话信息
        ImConversationVo conversationVo = getConversation(webSocketMessage, fromUserId);
        if (conversationVo == null) {
            return createErrorResult(fromUserId, ImResponseCodeEnum.NOT_CONVERSATION);
        }

        // 4. 根据会话类型和命令类型处理消息
        return processMessageByType(webSocketMessage, fromUserId, conversationVo);
    }

    /**
     * 解析消息内容
     *
     * @param message 消息内容
     * @return 解析后的WebSocketMessage对象，解析失败返回null
     */
    private WebSocketMessage parseMessage(String message) {
        WebSocketMessage webSocketMessage = JsonUtils.parseObject(message, WebSocketMessage.class);
        if (webSocketMessage == null) {
            log.error("[filter] 消息解析失败，格式错误");
        }
        return webSocketMessage;
    }

    /**
     * 验证消息基本结构
     *
     * @param webSocketMessage 消息对象
     * @param fromUserId 发送者ID
     * @return 验证结果
     */
    private FilterResult validateMessageStructure(WebSocketMessage webSocketMessage, Long fromUserId) {
        // 检查命令类型
        if (webSocketMessage.getCmd() == null) {
            log.error("[filter] 消息命令类型为空");
            sendErrorResponse(fromUserId, ImResponseCodeEnum.MESSAGE_INCOMPLETE);
            return FilterResult.error(ImResponseCodeEnum.MESSAGE_INCOMPLETE.getDescChinese(), 
                                      ImResponseCodeEnum.MESSAGE_INCOMPLETE.getCode());
        }

        // 检查会话ID
        if (webSocketMessage.getRoute() == null || webSocketMessage.getRoute().getConversationId() == null) {
            log.error("[filter] 会话ID为空");
            sendErrorResponse(fromUserId, ImResponseCodeEnum.MESSAGE_INCOMPLETE);
            return FilterResult.error(ImResponseCodeEnum.MESSAGE_INCOMPLETE.getDescChinese(), 
                                      ImResponseCodeEnum.MESSAGE_INCOMPLETE.getCode());
        }

        // 检查必要的消息字段
        if (webSocketMessage.getHeader() == null || webSocketMessage.getContent() == null) {
            log.error("[filter] 消息头或内容为空");
            sendErrorResponse(fromUserId, ImResponseCodeEnum.MESSAGE_INCOMPLETE);
            return FilterResult.error(ImResponseCodeEnum.MESSAGE_INCOMPLETE.getDescChinese(), 
                                      ImResponseCodeEnum.MESSAGE_INCOMPLETE.getCode());
        }

        return FilterResult.success();
    }

    /**
     * 获取会话信息
     *
     * @param webSocketMessage 消息对象
     * @param fromUserId 发送者ID
     * @return 会话信息，如果不存在返回null
     */
    private ImConversationVo getConversation(WebSocketMessage webSocketMessage, Long fromUserId) {
        Long conversationId = webSocketMessage.getRoute().getConversationId();
        ImConversationVo conversationVo = imConversationService.queryById(conversationId);
        
        if (conversationVo == null) {
            log.error("[filter] 会话不存在, conversationId:{}", conversationId);
            sendErrorResponse(fromUserId, ImResponseCodeEnum.NOT_CONVERSATION);
        }
        
        return conversationVo;
    }

    /**
     * 根据会话类型和命令类型处理消息
     *
     * @param webSocketMessage 消息对象
     * @param fromUserId 发送者ID
     * @param conversationVo 会话信息
     * @return 处理结果
     */
    private FilterResult processMessageByType(WebSocketMessage webSocketMessage, Long fromUserId, 
                                             ImConversationVo conversationVo) {
        int cmd = webSocketMessage.getCmd();
        int conversationType = conversationVo.getConversationType().intValue();

        // 陌生人单聊
        if (conversationType == ConversationTypeEnum.STRANGER_CHAT.getCode() && 
            cmd == RequestCmdEnum.STRANGER_CHAT.getCode()) {
            log.info("[filter] 处理陌生人单聊消息, conversationId:{}, fromUserId:{}", 
                     conversationVo.getId(), fromUserId);
            return processPrivateChat(fromUserId, webSocketMessage, conversationVo);
        }

        // 群聊
        if (conversationType == ConversationTypeEnum.GROUP.getCode() && 
            cmd == RequestCmdEnum.GROUP_CHAT.getCode()) {
            log.info("[filter] 处理群聊消息, conversationId:{}, fromUserId:{}", 
                     conversationVo.getId(), fromUserId);
            return processGroupChat(fromUserId, webSocketMessage, conversationVo);
        }

        // 单聊
        if (conversationType == ConversationTypeEnum.SINGLE.getCode() && 
            cmd == RequestCmdEnum.SINGLE_CHAT.getCode()) {
            log.info("[filter] 处理单聊消息, conversationId:{}, fromUserId:{}", 
                     conversationVo.getId(), fromUserId);
            return processPrivateChat(fromUserId, webSocketMessage, conversationVo);
        }

        // 不支持的消息类型
        log.warn("[filter] 不支持的消息类型, cmd:{}, conversationType:{}", cmd, conversationType);
        return createErrorResult(fromUserId, ImResponseCodeEnum.UNSUPPORTED_MESSAGE_TYPE);
    }

    /**
     * 处理私聊消息（包括单聊和陌生人聊天）
     *
     * @param fromUserId 发送者ID
     * @param webSocketMessage 消息对象
     * @param conversationVo 会话信息
     * @return 处理结果
     */
    private FilterResult processPrivateChat(Long fromUserId, WebSocketMessage webSocketMessage, 
                                           ImConversationVo conversationVo) {
        Long conversationId = webSocketMessage.getRoute().getConversationId();
        
        // 1. 获取会话成员列表
        List<Long> memberIds = getConversationMemberIds(conversationId);
        
        // 2. 验证发送者是否在会话中
        if (!memberIds.contains(fromUserId)) {
            return createErrorResult(fromUserId, ImResponseCodeEnum.SENDER_NOT_IN_CONVERSATION);
        }
        
        // 3. 获取接收者ID（私聊中除发送者外的另一个用户）
        List<Long> receiverIds = new ArrayList<>(memberIds);
        receiverIds.remove(fromUserId);
        
        Long receiverId = getReceiverId(fromUserId, receiverIds);
        if (receiverId == null) {
            return createErrorResult(fromUserId, ImResponseCodeEnum.RECEIVER_NOT_IN_CONVERSATION);
        }

        // 4. 构建消息对象并存储
        ImMessageBo messageBo = buildMessageBo(fromUserId, webSocketMessage, conversationVo);
        
        // 5. 设置接收者列表（包括发送者自己，用于多端同步）
        List<Long> allReceiverIds = new ArrayList<>();
        allReceiverIds.add(receiverId);
        allReceiverIds.add(fromUserId);

        // 6. 存储消息
        boolean storeSuccess = storeMessage(messageBo, webSocketMessage, allReceiverIds, fromUserId);
        return storeSuccess ? FilterResult.success() : 
               createErrorResult(fromUserId, ImResponseCodeEnum.MESSAGE_STORE_FAILED);
    }

    /**
     * 处理群聊消息
     *
     * @param fromUserId 发送者ID
     * @param webSocketMessage 消息对象
     * @param conversationVo 会话信息
     * @return 处理结果
     */
    private FilterResult processGroupChat(Long fromUserId, WebSocketMessage webSocketMessage, 
                                         ImConversationVo conversationVo) {
        Long conversationId = webSocketMessage.getRoute().getConversationId();
        
        // 1. 获取群成员列表
        List<Long> memberIds = getConversationMemberIds(conversationId);
        
        // 2. 验证发送者是否在群中
        if (!memberIds.contains(fromUserId)) {
            return createErrorResult(fromUserId, ImResponseCodeEnum.SENDER_NOT_IN_CONVERSATION);
        }

        // 3. 检查发送者是否被禁言
        if (isMemberMuted(conversationId, fromUserId)) {
            return createErrorResult(fromUserId, ImResponseCodeEnum.USER_MUTED);
        }

        // 4. 检查群组状态
        if (!isGroupActive(conversationVo)) {
            return createErrorResult(fromUserId, ImResponseCodeEnum.GROUP_DISSOLVED);
        }

        // 5. 构建消息对象并存储
        ImMessageBo messageBo = buildMessageBo(fromUserId, webSocketMessage, conversationVo);
        
        // 6. 存储消息
        try {
            boolean storeSuccess = storeMessage(messageBo, webSocketMessage, memberIds, fromUserId);
            return storeSuccess ? FilterResult.success() : 
                   createErrorResult(fromUserId, ImResponseCodeEnum.MESSAGE_STORE_FAILED);
        } catch (Exception e) {
            log.error("[filter] 群聊消息处理失败", e);
            return createErrorResult(fromUserId, ImResponseCodeEnum.MESSAGE_PROCESS_FAILED);
        }
    }

    /**
     * 获取会话成员ID列表
     *
     * @param conversationId 会话ID
     * @return 成员ID列表
     */
    private List<Long> getConversationMemberIds(Long conversationId) {
        ImConversationMemberBo queryBo = new ImConversationMemberBo();
        queryBo.setFkConversationId(conversationId);
        
        return conversationMemberService.queryList(queryBo)
            .stream()
            .map(ImConversationMemberVo::getFkUserId)
            .collect(Collectors.toList());
    }

    /**
     * 获取接收者ID
     *
     * @param fromUserId 发送者ID
     * @param receiverIds 可能的接收者ID列表
     * @return 接收者ID，如果没有合适的接收者返回null
     */
    private Long getReceiverId(Long fromUserId, List<Long> receiverIds) {
        if (receiverIds.isEmpty()) {
            sendErrorResponse(fromUserId, ImResponseCodeEnum.RECEIVER_NOT_IN_CONVERSATION);
            return null;
        }
        return receiverIds.get(0);
    }

    /**
     * 检查群成员是否被禁言
     *
     * @param conversationId 会话ID
     * @param userId 用户ID
     * @return 是否被禁言
     */
    private boolean isMemberMuted(Long conversationId, Long userId) {
        ImConversationMemberBo queryBo = new ImConversationMemberBo();
        queryBo.setFkConversationId(conversationId);
        queryBo.setFkUserId(userId);
        
        List<ImConversationMemberVo> memberList = conversationMemberService.queryList(queryBo);
        
        if (!memberList.isEmpty()) {
            ImConversationMemberVo member = memberList.get(0);
            return member.getMuteEndTime() != null && 
                   member.getMuteEndTime().getTime() > System.currentTimeMillis();
        }
        
        return false;
    }

    /**
     * 检查群组是否处于活跃状态
     *
     * @param conversationVo 会话信息
     * @return 群组是否活跃
     */
    private boolean isGroupActive(ImConversationVo conversationVo) {
        return conversationVo.getConversationStatus() == null || conversationVo.getConversationStatus() == 1;
    }

    /**
     * 存储消息
     *
     * @param messageBo 消息对象
     * @param webSocketMessage 原始WebSocket消息
     * @param receiverIds 接收者ID列表
     * @param fromUserId 发送者ID
     * @return 存储是否成功
     */
    private boolean storeMessage(ImMessageBo messageBo, WebSocketMessage webSocketMessage, 
                                List<Long> receiverIds, Long fromUserId) {
        boolean storeSuccess = imStoreHandler.store(messageBo, webSocketMessage, receiverIds);
        if (!storeSuccess) {
            log.error("[filter] 保存消息失败 message:{}", webSocketMessage);
            sendErrorResponse(fromUserId, ImResponseCodeEnum.MESSAGE_STORE_FAILED);
        }
        return storeSuccess;
    }

    /**
     * 创建错误结果并发送错误响应
     *
     * @param userId 用户ID
     * @param responseCode 响应码
     * @return 错误结果
     */
    private FilterResult createErrorResult(Long userId, ImResponseCodeEnum responseCode) {
        sendErrorResponse(userId, responseCode);
        return FilterResult.error(responseCode.getDescChinese(), responseCode.getCode());
    }

    /**
     * 构建消息对象
     *
     * @param fromUserId 发送者ID
     * @param webSocketMessage WebSocket消息
     * @param conversationVo 会话信息
     * @return 消息对象
     */
    private ImMessageBo buildMessageBo(Long fromUserId, WebSocketMessage webSocketMessage, 
                                      ImConversationVo conversationVo) {
        ImMessageBo messageBo = new ImMessageBo();
        
        // 设置基本信息
        messageBo.setFkConversationId(webSocketMessage.getRoute().getConversationId());
        messageBo.setFkFromUserId(fromUserId);
        messageBo.setMsgStatus(1L);
        messageBo.setCmd(Long.valueOf(webSocketMessage.getCmd()));
        messageBo.setPersistent(1L);
        messageBo.setPriority(1L);
        messageBo.setLocalMsgId(webSocketMessage.getHeader().getLocalId());
        messageBo.setMsgType(webSocketMessage.getRoute().getType() != null ?
            Long.valueOf(webSocketMessage.getRoute().getType()) : null);
        messageBo.setMsgText(webSocketMessage.getContent().getText() != null ? 
            webSocketMessage.getContent().getText() : "");
        messageBo.setConversationType(conversationVo.getConversationType());

        // 处理@提及
        processMentions(messageBo, webSocketMessage);
        
        // 处理引用消息
        processQuote(messageBo, webSocketMessage);
        
        // 处理扩展字段
        messageBo.setExtras(webSocketMessage.getContent().getExtension() != null ?
            webSocketMessage.getContent().getExtension() : "{}");
        
        // 处理富文本内容
        processContentItems(messageBo, webSocketMessage);
        
        // 处理接收者信息
        processReceiverInfo(messageBo, webSocketMessage);
        
        // 设置其他默认值
        messageBo.setRefCount(0L); // 被引用次数
        messageBo.setDeleted(0L);  // 未删除
        messageBo.setNeedReceipt(1L); // 需要回执
        
        return messageBo;
    }

    /**
     * 处理消息中的@提及信息
     *
     * @param messageBo 消息对象
     * @param webSocketMessage WebSocket消息
     */
    private void processMentions(ImMessageBo messageBo, WebSocketMessage webSocketMessage) {
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
    }

    /**
     * 处理消息中的引用信息
     *
     * @param messageBo 消息对象
     * @param webSocketMessage WebSocket消息
     */
    private void processQuote(ImMessageBo messageBo, WebSocketMessage webSocketMessage) {
        if (webSocketMessage.getContent().getQuote() != null) {
            Quote quote = webSocketMessage.getContent().getQuote();
            messageBo.setRefType(1L); // 引用消息
            messageBo.setRootMsgId(0L); // 暂时设为0，需要根据实际业务查找原始消息
            messageBo.setParentMsgId(quote.getMsgId() != null ? 
                Long.parseLong(quote.getMsgId()) : 0L);
        } else {
            messageBo.setRefType(0L); // 原创消息
            messageBo.setRootMsgId(0L);
            messageBo.setParentMsgId(0L);
        }
    }

    /**
     * 处理消息中的富文本内容
     *
     * @param messageBo 消息对象
     * @param webSocketMessage WebSocket消息
     */
    private void processContentItems(ImMessageBo messageBo, WebSocketMessage webSocketMessage) {
        if (webSocketMessage.getContent().getItems() != null && 
            !webSocketMessage.getContent().getItems().isEmpty()) {
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
    }

    /**
     * 处理消息的接收者信息
     *
     * @param messageBo 消息对象
     * @param webSocketMessage WebSocket消息
     */
    private void processReceiverInfo(ImMessageBo messageBo, WebSocketMessage webSocketMessage) {
        if (webSocketMessage.getRoute().getTarget() != null && 
            !webSocketMessage.getRoute().getTarget().isEmpty()) {
            messageBo.setReceiverOnly(String.join(",",
                webSocketMessage.getRoute().getTarget().stream()
                    .map(String::valueOf)
                    .toList()));
            messageBo.setReceiverCount((long) webSocketMessage.getRoute().getTarget().size());
        } else {
            messageBo.setReceiverOnly("");
            messageBo.setReceiverCount(1L);
        }
    }

    /**
     * 发送错误响应
     *
     * @param userId 用户ID
     * @param responseCode 响应码
     */
    private void sendErrorResponse(Long userId, ImResponseCodeEnum responseCode) {
        WebSocketMessage errorResponse = new WebSocketMessage();
        errorResponse.setDirection(MessageDirectionEnum.RESPONSE.getCode());
        
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
}
