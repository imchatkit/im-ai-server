package com.imai.handler.filter;

import com.imai.handler.ImSendMsg;
import com.imai.handler.dispatcher.ImDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author wei
 * @date 2025/1/16 10:45
 */
@DubboService
@Slf4j
public class ImMsgFilterHandlerImpl implements ImMsgFilterHandler {

    @DubboReference
    private ImSendMsg imSendMsg;

    @Override
    public boolean filter(String message, String userId, String channelId) {
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
