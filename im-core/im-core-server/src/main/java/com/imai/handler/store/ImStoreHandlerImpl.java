package com.imai.handler.store;

import com.baomidou.lock.annotation.Lock4j;
import com.imai.core.domain.bo.ImMessageBo;
import com.imai.core.domain.bo.ImMsgReceiverBo;
import com.imai.core.service.IImConversationSeqService;
import com.imai.core.service.IImMessageService;
import com.imai.core.service.IImMsgReceiverService;
import com.imai.handler.sync.ImSyncHandlerImpl;
import com.imai.ws.MessageExtra;
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
public class ImStoreHandlerImpl implements ImStoreHandler {
    @Resource
    private IImMessageService messageService;

    @Resource
    private IImConversationSeqService conversationSeqService;

    @Resource
    private IImMsgReceiverService msgReceiverService;

    @Resource
    private ImSyncHandlerImpl imSyncHandler;

    /**
     * 保存消息
     *
     * @param messageBo
     * @param webSocketMessage
     * @param receiverIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Lock4j(keys = {"${webSocketMessage.route.conversationId}"}, expire = 30000L)
    public boolean store(ImMessageBo messageBo, WebSocketMessage webSocketMessage, List<Long> receiverIds) {

        // 获取并递增会话序列号
        Long conversationSeq = conversationSeqService.getAndIncrementSeq(webSocketMessage.getRoute().getConversationId());
        messageBo.setConversationSeq(conversationSeq);

        Boolean msgSaveResult = messageService.insertByBo(messageBo);
        if (!msgSaveResult) {
            log.error("[store] 保存消息失败 message:{}", webSocketMessage);
            throw new RuntimeException("保存消息失败");
        }
        log.info("[store] 保存消息成功 message:{}", webSocketMessage);

        MessageExtra messageExtra = new MessageExtra();
        messageExtra.setMessageId(messageBo.getId());
        webSocketMessage.setMessageExtra(messageExtra);


        for (Long receiverId : receiverIds) {
            // 持久化用户接收表-发送方
            ImMsgReceiverBo imMsgReceiverBo = new ImMsgReceiverBo();
            imMsgReceiverBo.setFkMsgId(messageBo.getId());
            imMsgReceiverBo.setFkReceiverId(receiverId);
            imMsgReceiverBo.setDeleted(0L);
            if (!msgReceiverService.insertByBo(imMsgReceiverBo)) {
                log.error("[store] 保存消息接收失败 message:{}", webSocketMessage);
                throw new RuntimeException("保存消息接收失败");
            }
        }

        // 调用消息同步
        if (!imSyncHandler.sync(webSocketMessage, receiverIds)) {
            log.error("[store] 消息同步失败 message:{}", webSocketMessage);
            throw new RuntimeException("消息同步失败");
        }

        return true;
    }

}
