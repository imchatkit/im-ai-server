package com.imai.handler.filter;

import com.imai.handler.ImSendMsg;
import com.imai.core.service.IImConversationMemberService;

import com.imai.ws.WebSocketMessage;
import com.imai.ws.enums.CmdType;
import com.imai.ws.enums.MessageDirection;
import com.imai.ws.enums.ImResponseCode;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.common.json.utils.JsonUtils;

/**
 * 过滤器,拦截消息,例如群聊判断用户是否在群内,
 */
@DubboService
@Slf4j
public class ImMsgFilterHandlerImpl implements ImMsgFilterHandler {

    @DubboReference
    private ImSendMsg imSendMsg;

    @DubboReference
    private IImConversationMemberService conversationMemberService;

    /**
     * 过滤消息
     * @param message 消息
     * @param fromUserId 用户id
     * @param channelId 通道id
     * @return 是否过滤
     */
    @Override
    public boolean filter(String message, Long fromUserId, String channelId) {
        log.info("[filter] start - message:{}, userId:{}", message, fromUserId);

        // 处理cmd
        WebSocketMessage webSocketMessage = JsonUtils.parseObject(message, WebSocketMessage.class);
        Integer cmd = webSocketMessage.getCmd();

        // 陌生人单聊
        if (cmd == CmdType.STRANGER_CHAT.getCode() ) {
                Long conversationId = webSocketMessage.getRoute().getConversationId();
                Long to = webSocketMessage.getRoute().getTo();
                webSocketMessage.getRoute().setFrom(fromUserId);

                // 判断to是否在conversationId中
                boolean isInConversation = conversationMemberService.contains(conversationId, to);

                if (!isInConversation) {
                    // 发送错误响应
                    WebSocketMessage errorResponse = WebSocketMessage.builder()
                        .direction(MessageDirection.RESPONSE.getCode())
                        .code(ImResponseCode.RECEIVER_NOT_IN_CONVERSATION.getCode())
                        .message(ImResponseCode.RECEIVER_NOT_IN_CONVERSATION.getDescChinese())
                        .build();
                    
                    try {
                        imSendMsg.sendMsgToUser(JsonUtils.toJsonString(errorResponse), fromUserId);
                    } catch (Exception e) {
                        log.error("[filter] 发送错误响应失败", e);
                    }
                    return false;
                }

                // 判断from是否在conversationId中
                boolean isFromInConversation = conversationMemberService.contains(conversationId, fromUserId);

                if (!isFromInConversation) {

                    // 发送错误响应
                    WebSocketMessage errorResponse = WebSocketMessage.builder()
                        .direction(MessageDirection.RESPONSE.getCode())
                        .code(ImResponseCode.SENDER_NOT_IN_CONVERSATION.getCode())
                        .message(ImResponseCode.SENDER_NOT_IN_CONVERSATION.getDescChinese())
                        .build();

                    try {
                        imSendMsg.sendMsgToUser(JsonUtils.toJsonString(errorResponse), fromUserId);
                    } catch (Exception e) {
                        log.error("[filter] 发送错误响应失败", e);
                    }

                    return false;
                }


        }

        if (cmd == CmdType.GROUP_CHAT.getCode() ) {
         
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
}
