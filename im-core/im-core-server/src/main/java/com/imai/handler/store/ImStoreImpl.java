package com.imai.handler.store;

import com.imai.core.domain.bo.ImMessageBo;
import com.imai.core.domain.bo.ImMsgReceiverBo;
import com.imai.core.service.IImMessageService;
import com.imai.core.service.IImMsgReceiverService;
import com.imai.handler.sync.ImSyncImpl;
import com.imai.ws.WebSocketMessage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wei
 * @date 2025/1/10 10:31
 */
@DubboService
@Slf4j
public class ImStoreImpl implements ImStore {
    @Resource
    private IImMessageService messageService;

    @Resource
    private IImMsgReceiverService msgReceiverService;

    @Resource
    private ImSyncImpl imSyncImpl;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean store(ImMessageBo messageBo, WebSocketMessage webSocketMessage, List<Long> receiverIds) {

        Boolean msgSaveResult = messageService.insertByBo(messageBo);
        if (!msgSaveResult) {
            log.error("[filter] 保存消息失败 message:{}", webSocketMessage);
            throw new RuntimeException("保存消息失败");
        }
        log.info("[filter] 保存消息成功 message:{}", webSocketMessage);

        // 持久化用户接收表-发送方
        ImMsgReceiverBo imMsgReceiverBo = new ImMsgReceiverBo();
        imMsgReceiverBo.setFkMsgId(messageBo.getId());
        imMsgReceiverBo.setFkReceiverId(webSocketMessage.getRoute().getFrom());
        imMsgReceiverBo.setDeleted(0L);
        Boolean b = msgReceiverService.insertByBo(imMsgReceiverBo);

        // 持久化用户接收表-接收方
        ImMsgReceiverBo imMsgReceiverBoTo = new ImMsgReceiverBo();
        imMsgReceiverBoTo.setFkMsgId(messageBo.getId());
        imMsgReceiverBoTo.setFkReceiverId(webSocketMessage.getRoute().getTo());
        imMsgReceiverBoTo.setDeleted(0L);
        msgReceiverService.insertByBo(imMsgReceiverBoTo);

        // 调用消息同步
        imSyncImpl.sync(webSocketMessage,receiverIds);

        return true;
    }
}
