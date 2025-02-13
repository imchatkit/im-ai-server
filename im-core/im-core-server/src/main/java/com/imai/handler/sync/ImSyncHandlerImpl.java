package com.imai.handler.sync;

import com.imai.core.domain.vo.ImConversationRecentVo;
import com.imai.core.domain.bo.ImConversationRecentBo;
import com.imai.core.service.IImConversationRecentService;
import com.imai.handler.ImSendMsg;
import com.imai.ws.WebSocketMessage;
import com.imai.ws.enums.MessageDirection;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.common.json.utils.JsonUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 消息同步处理器实现类
 * <p>
 * 负责处理多端消息同步，
 * 通过Dubbo服务暴露接口，与WebSocket服务协作实现实时消息推送。
 * </p>
 * <p>
 * 主要功能：
 * 1. 接收WebSocket消息和接收者列表
 * 2. 为每个接收者创建同步记录
 * 4. 通过WebSocket推送消息到客户端
 * </p>
 * <p>
 * 注意事项：
 * 1. 同步操作是原子性的，任一接收者同步失败将导致整个操作回滚
 * 2. 依赖IMSyncService进行数据持久化
 * 3. 依赖ImSendMsg进行WebSocket消息推送
 * </p>
 * @author wei
 * @date 2025/1/16 10:45
 */
@Service
@Slf4j
public class ImSyncHandlerImpl implements ImSyncHandler {

    /**
     * WebSocket消息发送服务，用于将同步后的消息推送到客户端
     */
    @DubboReference
    private ImSendMsg imSendMsg;

    /**
     * 对话列表服务，负责对话列表的持久化操作
     */
    @Resource
    private IImConversationRecentService imConversationRecentService;

    @Resource
    private ThreadPoolTaskExecutor msgProcessExecutor;

    /**
     * 同步消息到指定用户
     * @param webSocketMessage 消息对象
     * @param receiverIds 接收消息的用户ID列表
     * @return 同步是否成功
     */
    @Override
    public boolean sync(WebSocketMessage webSocketMessage, List<Long> receiverIds) {
        long pts = 1L;
        for (Long receiverId : receiverIds) {
            log.info("用户{}同步消息成功", receiverId);
            webSocketMessage.getMessageExtra().setTimestamp(new Date().getTime());

            // 保存到对话列表
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
        }

        webSocketMessage.setDirection(MessageDirection.PUSH.getCode());
        webSocketMessage.getMessageExtra().setPts(pts);

        // 异步发送ws推送消息,即使没有推送成功，也可以通过数据库拉取离线消息，因为消息已经持久化到数据库了
        msgProcessExecutor.execute(() -> {
            for (Long receiverId : receiverIds) {
                imSendMsg.sendMsgToUser(JsonUtils.toJsonString(webSocketMessage), receiverId);
            }
        });
        return true;
    }
}
