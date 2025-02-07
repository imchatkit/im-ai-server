package com.imai.handler.sync;

import com.imai.core.domain.bo.ImSyncBo;
import com.imai.core.service.IImSyncService;
import com.imai.handler.ImSendMsg;
import com.imai.ws.WebSocketMessage;
import com.imai.ws.enums.ImResponseCode;
import com.imai.ws.enums.MessageDirection;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.common.json.utils.JsonUtils;

import java.util.List;

/**
 * @author wei
 * @date 2025/1/16 10:45
 */
@DubboService
@Slf4j
public class ImSyncHandlerImpl implements ImSyncHandler {

    @Resource
    private IImSyncService imSyncService;


    @DubboReference
    private ImSendMsg imSendMsg;

    @Override
    public boolean sync(WebSocketMessage webSocketMessage, List<Long> receiverIds) {

        for (Long receiverId : receiverIds) {
            ImSyncBo imSyncBo = new ImSyncBo();
            imSyncBo.setFkUserId(receiverId);
            imSyncBo.setPts(1L);
            imSyncBo.setFkMsgId(webSocketMessage.getMessageExtra().getMessageId());
            imSyncBo.setDeleted(0L);
            imSyncBo.setExtras(null);
            imSyncService.insertByBo(imSyncBo);
            log.info("用户{}同步消息成功", receiverId);
        }


        webSocketMessage.settingDirection(MessageDirection.PUSH.getCode() , ImResponseCode.SUCCESS.getCode() , ImResponseCode.SUCCESS.getDescChinese() , null);

        // 发送ws推送消息
        for (Long receiverId : receiverIds) {
            imSendMsg.sendMsgToUser(JsonUtils.toJsonString(webSocketMessage), receiverId);
        }


        return true;
    }
}
