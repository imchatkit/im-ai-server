package com.imai.handler.sync;

import com.imai.core.service.IImConversationRecentService;
import com.imai.handler.ImSendMsg;
import com.imai.ws.WebSocketMessage;
import com.imai.ws.enums.MessageDirection;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
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
 *
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

    @Resource
    private ThreadPoolTaskExecutor msgProcessExecutor;

    /**
     * 同步消息到指定用户
     *
     * @param webSocketMessage 消息对象
     * @param receiverIds      接收消息的用户ID列表
     * @return 同步是否成功
     */
    @Override
    public boolean sync(WebSocketMessage webSocketMessage, List<Long> receiverIds) {


        // 设置消息同步序号
        long pts = 1L;
        webSocketMessage.setDirection(MessageDirection.PUSH.getCode());
        webSocketMessage.getMessageExtra().setPts(pts);
        webSocketMessage.getMessageExtra().setTimestamp(new Date().getTime());

        // 异步发送ws推送消息
        msgProcessExecutor.execute(() -> {
            for (Long receiverId : receiverIds) {
                imSendMsg.sendMsgToUser(JsonUtils.toJsonString(webSocketMessage), receiverId);
            }
        });
        return true;
    }


}
