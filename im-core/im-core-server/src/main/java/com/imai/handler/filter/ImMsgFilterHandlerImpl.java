package com.imai.handler.filter;

import com.imai.handler.ImSendMsg;
import com.imai.handler.dispatcher.ImDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 过滤器,拦截消息,例如群聊判断用户是否在群内, 
 */
@DubboService
@Slf4j
public class ImMsgFilterHandlerImpl implements ImMsgFilterHandler {

    @DubboReference
    private ImSendMsg imSendMsg;

    /**
     * 过滤消息
     * @param message 消息
     * @param userId 用户id
     * @param channelId 通道id
     * @return 是否过滤
     */
    @Override
    public boolean filter(String message, String userId, String channelId) {
        // 要先判断会话类型

        // // 判断是否在群内
        // boolean isInGroup = isInGroup(message, userId);
        // log.info("[filter] isInGroup:{}", isInGroup);

        try {
            log.info("[filter] start - message:{}, userId:{}", message, userId);
            boolean result = imSendMsg.sendMsgToUser("RPC: filterRes:" + message, userId);
            log.info("[filter] end - result:{}", result);
            return result;
        } catch (Exception e) {
            log.error("[filter] error", e);
            return false;
        }
    }
}
