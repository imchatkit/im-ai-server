package com.imai.handler.store;

import com.imai.core.domain.bo.ImConversationRecentBo;
import com.imai.core.domain.bo.ImMessageBo;
import com.imai.core.domain.bo.ImMsgReceiverBo;
import com.imai.core.domain.vo.ImConversationRecentVo;
import com.imai.core.service.IImConversationRecentService;
import com.imai.core.service.IImMessageService;
import com.imai.core.service.IImMsgReadService;
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
    private IImMsgReadService imMsgReadService; // 消息已读状态服务

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

            // 批量保存消息接收箱记录
            saveMessageReceivers(messageBo.getId(), receiverIds);

            // 批量更新会话列表
            saveConversationRecent(webSocketMessage, receiverIds);

            // 创建消息已读记录
            if (!imMsgReadService.batchCreateMsgRead(messageBo.getId(), webSocketMessage.getRoute().getConversationId(), messageBo.getFkFromUserId(), receiverIds)) {
                log.error("[store] 批量创建消息已读记录失败");
                throw new RuntimeException("批量创建消息已读记录失败");
            }

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
                // 创建查询条件对象
                ImConversationRecentBo queryBo = new ImConversationRecentBo();
                queryBo.setFkUserId(receiverId);
                queryBo.setFkConversationId(webSocketMessage.getRoute().getConversationId());

                // 查询是否存在记录
                List<ImConversationRecentVo> existingRecords = imConversationRecentService.queryList(queryBo);
                ImConversationRecentBo conversationRecentBo = new ImConversationRecentBo();

                // 设置基本信息
                conversationRecentBo.setFkUserId(receiverId);
                conversationRecentBo.setFkConversationId(webSocketMessage.getRoute().getConversationId());
                conversationRecentBo.setLastMsgId(webSocketMessage.getMessageExtra().getMessageId());
                conversationRecentBo.setLastMsgTime(new Date());

                if (!existingRecords.isEmpty()) {
                    // 记录已存在，更新现有记录
                    ImConversationRecentVo existingRecord = existingRecords.get(0);
                    conversationRecentBo.setId(existingRecord.getId());
                    conversationRecentBo.setNoReadCount(existingRecord.getNoReadCount() + 1);
                    if (!imConversationRecentService.updateByBo(conversationRecentBo)) {
                        log.error("[store] 更新会话列表失败 receiverId:{}", receiverId);
                        throw new RuntimeException("更新会话列表失败");
                    }
                } else {
                    // 记录不存在，创建新记录
                    conversationRecentBo.setNoReadCount(1L);
                    if (!imConversationRecentService.insertByBo(conversationRecentBo)) {
                        log.error("[store] 创建会话列表失败 receiverId:{}", receiverId);
                        throw new RuntimeException("创建会话列表失败");
                    }
                }
            } catch (Exception e) {
                log.error("[store] 更新会话列表失败 receiverId:{}", receiverId, e);
                throw new RuntimeException("更新会话列表失败");
            }
        }
    }


    /**
     * 处理系统消息（跳过常规过滤）
     *
     * @param messageBo        消息业务对象
     * @param webSocketMessage WebSocket消息对象
     * @param receiverIds      接收方ID列表
     * @return 消息处理是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean handleSystemMessage(ImMessageBo messageBo, WebSocketMessage webSocketMessage, List<Long> receiverIds) {
        log.info("handleSystemMessage:{}", webSocketMessage);
        return store(messageBo, webSocketMessage, receiverIds);
    }
}
