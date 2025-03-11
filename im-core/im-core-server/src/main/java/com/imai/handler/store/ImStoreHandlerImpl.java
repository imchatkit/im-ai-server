package com.imai.handler.store;

import com.imai.core.domain.bo.ImConversationRecentBo;
import com.imai.core.domain.bo.ImMessageBo;
import com.imai.core.domain.bo.ImMsgReceiverBo;
import com.imai.core.domain.vo.ImConversationRecentVo;
import com.imai.core.service.IImConversationRecentService;
import com.imai.core.service.IImMessageService;
import com.imai.core.service.IImMsgReceiverService;
import com.imai.handler.sync.ImSyncHandlerImpl;
import com.imai.ws.MessageExtra;
import com.imai.ws.WebSocketMessage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 消息存储处理器实现类
 * 主要职责：
 * 1. 处理消息的持久化存储
 * 2. 管理离线消息、已读未读状态
 * 3. 维护消息收件箱
 * 4. 管理会话序列号seq
 * <p>
 * 该类通过Dubbo服务暴露，供其他模块调用
 *
 * @author wei
 * @date 2025/1/10 10:31
 */
@Service
@Slf4j
public class ImStoreHandlerImpl implements ImStoreHandler {
    @Resource
    private IImMessageService messageService; // 消息服务，负责消息的CRUD操作

    @Resource
    private IImMsgReceiverService msgReceiverService; // 消息接收者服务，管理消息与接收者的关系

    @Resource
    private IImConversationRecentService imConversationRecentService; // 负责维护最近会话列表,消息未读数

    @Resource
    private ImSyncHandlerImpl imSyncHandler; // 消息同步处理器，负责消息的同步操作

    /**
     * 保存消息
     *
     * @param messageBo        消息业务对象
     * @param webSocketMessage WebSocket消息对象
     * @param receiverIds      接收方ID列表
     * @return 消息保存是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean store(ImMessageBo messageBo, WebSocketMessage webSocketMessage, List<Long> receiverIds) {
        try {
            // 设置应用ID
            messageBo.setAppId("0");

            // 保存消息到数据库
            Boolean msgSaveResult = messageService.insertByBo(messageBo);
            if (!msgSaveResult) {
                log.error("[store] 保存消息失败 message:{}", webSocketMessage);
                throw new RuntimeException("保存消息失败");
            }
            log.info("[store] 保存消息成功 message:{}", webSocketMessage);

            // 设置消息额外信息
            MessageExtra messageExtra = new MessageExtra();
            messageExtra.setMessageId(messageBo.getId());
            webSocketMessage.setMessageExtra(messageExtra);

            // 批量保存消息接收记录
            saveMessageReceivers(messageBo.getId(), receiverIds);

            // 批量更新会话列表
            saveConversationRecent(webSocketMessage, receiverIds);

            // 同步消息到其他系统
            if (!imSyncHandler.sync(webSocketMessage, receiverIds)) {
                log.error("[store] 消息同步失败 message:{}", webSocketMessage);
                throw new RuntimeException("消息同步失败");
            }

            return true;
        } catch (Exception e) {
            log.error("[store] 消息处理失败", e);
            throw e;
        }
    }

    /**
     * 批量保存消息接收记录
     */
    private void saveMessageReceivers(Long messageId, List<Long> receiverIds) {
        for (Long receiverId : receiverIds) {
            ImMsgReceiverBo imMsgReceiverBo = new ImMsgReceiverBo();
            imMsgReceiverBo.setFkMsgId(messageId);
            imMsgReceiverBo.setFkReceiverId(receiverId);
            imMsgReceiverBo.setDeleted(0L);
            if (!msgReceiverService.insertByBo(imMsgReceiverBo)) {
                log.error("[store] 保存消息接收失败 receiverId:{}", receiverId);
                throw new RuntimeException("保存消息接收失败");
            }
        }
    }

    /**
     * 批量更新会话列表
     */
    private void saveConversationRecent(WebSocketMessage webSocketMessage, List<Long> receiverIds) {
        for (Long receiverId : receiverIds) {
            try {
                ImConversationRecentBo conversationRecentBo = new ImConversationRecentBo();
                conversationRecentBo.setFkUserId(receiverId);
                conversationRecentBo.setFkConversationId(webSocketMessage.getRoute().getConversationId());
                conversationRecentBo.setLastMsgId(webSocketMessage.getMessageExtra().getMessageId());
                conversationRecentBo.setLastMsgTime(new Date());

                // 从数据库获取当前未读消息数并加1
                Long currentNoReadCount = imConversationRecentService.queryList(conversationRecentBo)
                    .stream()
                    .findFirst()
                    .map(ImConversationRecentVo::getNoReadCount)
                    .orElse(0L);

                conversationRecentBo.setNoReadCount(currentNoReadCount + 1);
                imConversationRecentService.insertByBo(conversationRecentBo);
            } catch (Exception e) {
                log.error("[store] 更新会话列表失败 receiverId:{}", receiverId, e);
                throw new RuntimeException("更新会话列表失败");
            }
        }
    }
}
