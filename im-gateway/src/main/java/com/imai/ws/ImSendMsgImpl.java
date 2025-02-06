package com.imai.ws;

import com.imai.handler.ImSendMsg;
import com.imai.ws.netty.user.SendMsgUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author wei
 * @date 2025/1/16 15:31
 */
@DubboService
@Slf4j
public class ImSendMsgImpl implements ImSendMsg {

    @Resource
    private SendMsgUtil sendMsgUtil;

    @Override
    public boolean sendMsgToUser(String message, Long userId) {
        try {
            log.info("[sendMsgToUser] start - message:{}, userId:{}", message, userId);
            sendMsgUtil.toUserAllChannel(userId, message);
            log.info("[sendMsgToUser] success");
            return true;
        } catch (Exception e) {
            log.error("[sendMsgToUser] error - message:{}, userId:{}", message, userId, e);
            return false;
        }
    }

    @Override
    public boolean sendMsgToChannel(String message, String channelId) {
        try {
            log.info("[sendMsgToChannel] start - message:{}, channelId:{}", message, channelId);
            // TODO: 实现发送到指定channel的逻辑
            log.info("[sendMsgToChannel] success");
            return true;
        } catch (Exception e) {
            log.error("[sendMsgToChannel] error - message:{}, channelId:{}", message, channelId, e);
            return false;
        }
    }
}
