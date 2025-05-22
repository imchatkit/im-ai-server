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

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 消息存储处理器实现类
 * 
 * <p>该类负责处理即时消息的持久化存储、消息状态管理和会话维护。作为Dubbo服务提供者，
 * 它为其他模块提供消息存储和系统消息处理服务。</p>
 *
 * <p>主要职责：</p>
 * <ul>
 *   <li>处理消息的持久化存储到数据库</li>
 *   <li>管理离线消息和消息的已读/未读状态</li>
 *   <li>维护用户消息收件箱</li>
 *   <li>管理会话序列号和最近会话列表</li>
 *   <li>协调消息的存储和同步流程</li>
 * </ul>
 *
 * @author wei
 * @since 2025/1/10
 */
@Service
@Slf4j
public class ImStoreHandlerImpl implements ImStoreHandler {
    
    @Resource
    @Lazy
    private IImMessageService messageService;

    @Resource
    @Lazy
    private IImMsgReceiverService msgReceiverService;

    @Resource
    @Lazy
    private IImConversationRecentService imConversationRecentService;

    @Resource
    @Lazy
    private IImMsgReadService imMsgReadService;

    @Resource
    @Lazy
    private ImSyncHandlerImpl imSyncHandler;

    /**
     * 存储消息并处理相关数据
     * 
     * <p>该方法在一个事务中完成以下操作：</p>
     * <ol>
     *   <li>将消息保存到数据库</li>
     *   <li>为每个接收者创建消息接收记录</li>
     *   <li>更新或创建最近会话记录</li>
     *   <li>创建消息已读状态记录</li>
     *   <li>同步消息到其他系统</li>
     * </ol>
     *
     * @param messageBo 消息业务对象，包含消息的详细信息
     * @param webSocketMessage WebSocket消息对象，包含传输层消息信息
     * @param receiverIds 接收方ID列表，指定消息的接收者
     * @return 消息存储是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean store(ImMessageBo messageBo, WebSocketMessage webSocketMessage, List<Long> receiverIds) {
        if (messageBo == null || webSocketMessage == null || CollectionUtils.isEmpty(receiverIds)) {
            log.error("[ImStoreHandler] 存储消息失败：参数无效 messageBo={}, webSocketMessage={}, receiverIds={}", 
                    messageBo, webSocketMessage, receiverIds);
            return false;
        }
        
        try {
            // 设置应用ID
            messageBo.setAppId("0");

            // 保存消息到数据库
            if (!messageService.insertByBo(messageBo)) {
                log.error("[ImStoreHandler] 保存消息到数据库失败: {}", webSocketMessage);
                throw new RuntimeException("保存消息到数据库失败");
            }
            log.info("[ImStoreHandler] 保存消息成功: messageId={}", messageBo.getId());

            // 设置消息额外信息
            updateMessageExtra(webSocketMessage, messageBo.getId());

            // 批量保存消息接收箱记录
            saveMessageReceivers(messageBo.getId(), receiverIds);

            // 批量更新会话列表
            updateConversationRecent(webSocketMessage, receiverIds);

            // 创建消息已读记录
            createMessageReadRecords(messageBo, webSocketMessage, receiverIds);

            // 同步消息到其他系统
            syncMessageToOtherSystems(webSocketMessage, receiverIds);

            return true;
        } catch (Exception e) {
            log.error("[ImStoreHandler] 消息处理失败", e);
            throw e;
        }
    }

    /**
     * 更新WebSocket消息的额外信息
     */
    private void updateMessageExtra(WebSocketMessage webSocketMessage, Long messageId) {
        MessageExtra messageExtra = new MessageExtra();
        messageExtra.setMessageId(messageId);
        webSocketMessage.setMessageExtra(messageExtra);
    }

    /**
     * 创建消息已读记录
     */
    private void createMessageReadRecords(ImMessageBo messageBo, WebSocketMessage webSocketMessage, List<Long> receiverIds) {
        if (!imMsgReadService.batchCreateMsgRead(messageBo.getId(), 
                webSocketMessage.getRoute().getConversationId(), 
                messageBo.getFkFromUserId(), receiverIds)) {
            log.error("[ImStoreHandler] 批量创建消息已读记录失败: messageId={}", messageBo.getId());
            throw new RuntimeException("批量创建消息已读记录失败");
        }
        log.debug("[ImStoreHandler] 批量创建消息已读记录成功: messageId={}", messageBo.getId());
    }

    /**
     * 同步消息到其他系统
     */
    private void syncMessageToOtherSystems(WebSocketMessage webSocketMessage, List<Long> receiverIds) {
        if (!imSyncHandler.sync(webSocketMessage, receiverIds)) {
            log.error("[ImStoreHandler] 消息同步失败: {}", webSocketMessage);
            throw new RuntimeException("消息同步失败");
        }
        log.debug("[ImStoreHandler] 消息同步成功: messageId={}", 
                webSocketMessage.getMessageExtra().getMessageId());
    }

    /**
     * 批量保存消息接收记录
     *
     * @param messageId 消息ID
     * @param receiverIds 接收者ID列表
     */
    private void saveMessageReceivers(Long messageId, List<Long> receiverIds) {
        for (Long receiverId : receiverIds) {
            ImMsgReceiverBo receiverBo = new ImMsgReceiverBo();
            receiverBo.setFkMsgId(messageId);
            receiverBo.setFkReceiverId(receiverId);
            receiverBo.setDeleted(0L);
            
            if (!msgReceiverService.insertByBo(receiverBo)) {
                log.error("[ImStoreHandler] 保存消息接收记录失败: messageId={}, receiverId={}", messageId, receiverId);
                throw new RuntimeException("保存消息接收记录失败");
            }
        }
        log.debug("[ImStoreHandler] 批量保存消息接收记录成功: messageId={}, receiverCount={}", 
                messageId, receiverIds.size());
    }

    /**
     * 批量更新会话列表
     *
     * @param webSocketMessage WebSocket消息对象
     * @param receiverIds 接收者ID列表
     */
    private void updateConversationRecent(WebSocketMessage webSocketMessage, List<Long> receiverIds) {
        Long conversationId = webSocketMessage.getRoute().getConversationId();
        Long messageId = webSocketMessage.getMessageExtra().getMessageId();
        
        for (Long receiverId : receiverIds) {
            try {
                // 查询是否存在记录
                ImConversationRecentBo queryBo = createQueryBo(receiverId, conversationId);
                List<ImConversationRecentVo> existingRecords = imConversationRecentService.queryList(queryBo);
                
                // 准备更新或创建的会话记录
                ImConversationRecentBo conversationBo = createConversationBo(receiverId, conversationId, messageId);
                
                if (!existingRecords.isEmpty()) {
                    // 更新现有会话记录
                    updateExistingConversation(conversationBo, existingRecords.get(0));
                } else {
                    // 创建新会话记录
                    createNewConversation(conversationBo);
                }
            } catch (Exception e) {
                log.error("[ImStoreHandler] 更新会话列表失败: conversationId={}, receiverId={}", 
                        conversationId, receiverId, e);
                throw new RuntimeException("更新会话列表失败", e);
            }
        }
        log.debug("[ImStoreHandler] 批量更新会话列表成功: conversationId={}, receiverCount={}", 
                conversationId, receiverIds.size());
    }
    
    /**
     * 创建会话查询对象
     */
    private ImConversationRecentBo createQueryBo(Long userId, Long conversationId) {
        ImConversationRecentBo queryBo = new ImConversationRecentBo();
        queryBo.setFkUserId(userId);
        queryBo.setFkConversationId(conversationId);
        return queryBo;
    }
    
    /**
     * 创建会话记录对象
     */
    private ImConversationRecentBo createConversationBo(Long userId, Long conversationId, Long messageId) {
        ImConversationRecentBo conversationBo = new ImConversationRecentBo();
        conversationBo.setFkUserId(userId);
        conversationBo.setFkConversationId(conversationId);
        conversationBo.setLastMsgId(messageId);
        conversationBo.setLastMsgTime(new Date());
        return conversationBo;
    }
    
    /**
     * 更新现有会话记录
     */
    private void updateExistingConversation(ImConversationRecentBo conversationBo, ImConversationRecentVo existingRecord) {
        conversationBo.setId(existingRecord.getId());
        conversationBo.setNoReadCount(existingRecord.getNoReadCount() + 1);
        
        if (!imConversationRecentService.updateByBo(conversationBo)) {
            log.error("[ImStoreHandler] 更新会话记录失败: conversationId={}, userId={}", 
                    conversationBo.getFkConversationId(), conversationBo.getFkUserId());
            throw new RuntimeException("更新会话记录失败");
        }
    }
    
    /**
     * 创建新会话记录
     */
    private void createNewConversation(ImConversationRecentBo conversationBo) {
        conversationBo.setNoReadCount(1L);
        
        if (!imConversationRecentService.insertByBo(conversationBo)) {
            log.error("[ImStoreHandler] 创建会话记录失败: conversationId={}, userId={}", 
                    conversationBo.getFkConversationId(), conversationBo.getFkUserId());
            throw new RuntimeException("创建会话记录失败");
        }
    }

    /**
     * 处理系统消息
     * 
     * <p>系统消息是特殊类型的消息，通常跳过常规的消息过滤流程，直接进行存储和分发。</p>
     *
     * @param messageBo 消息业务对象
     * @param webSocketMessage WebSocket消息对象
     * @param receiverIds 接收方ID列表
     * @return 消息处理是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean handleSystemMessage(ImMessageBo messageBo, WebSocketMessage webSocketMessage, List<Long> receiverIds) {
        log.info("[ImStoreHandler] 处理系统消息: cmd={}, receiverCount={}", 
                webSocketMessage.getCmd(), receiverIds.size());
        return store(messageBo, webSocketMessage, receiverIds);
    }
}
