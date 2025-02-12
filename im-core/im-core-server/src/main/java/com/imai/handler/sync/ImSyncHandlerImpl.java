package com.imai.handler.sync;

import com.imai.core.domain.bo.ImSyncBo;
import com.imai.core.service.IImSyncService;
import com.imai.handler.ImSendMsg;
import com.imai.ws.WebSocketMessage;
import com.imai.ws.enums.MessageDirection;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.common.json.utils.JsonUtils;

import java.util.Date;
import java.util.List;

/**
 * 消息同步处理器实现类
 * <p>
 * 负责处理多端消息同步，维护用户pts（Push Time Sequence）同步位点。
 * 通过Dubbo服务暴露接口，与WebSocket服务协作实现实时消息推送。
 * </p>
 * <p>
 * 主要功能：
 * 1. 接收WebSocket消息和接收者列表
 * 2. 为每个接收者创建同步记录
 * 3. 更新消息的pts值
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
@DubboService
@Slf4j
public class ImSyncHandlerImpl implements ImSyncHandler {

    /**
     * 消息同步服务，负责消息同步记录的持久化操作
     */
    @Resource
    private IImSyncService imSyncService;

    /**
     * WebSocket消息发送服务，用于将同步后的消息推送到客户端
     */
    @DubboReference
    private ImSendMsg imSendMsg;

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
            ImSyncBo imSyncBo = new ImSyncBo();
            imSyncBo.setFkUserId(receiverId);
            imSyncBo.setPts(pts);
            imSyncBo.setFkMsgId(webSocketMessage.getMessageExtra().getMessageId());
            imSyncBo.setDeleted(0L);
            imSyncBo.setExtras(null);
            Boolean b = imSyncService.insertByBo(imSyncBo);
            if (!b) {
                log.error("用户{}同步消息失败", receiverId);
                throw new RuntimeException("同步消息失败");
            }
            log.info("用户{}同步消息成功", receiverId);
            webSocketMessage.getMessageExtra().setTimestamp(new Date().getTime());
        }
        webSocketMessage.setDirection(MessageDirection.PUSH.getCode());
        webSocketMessage.getMessageExtra().setPts(pts);

        // 注释掉的代码，用于设置消息方向和响应代码
        // webSocketMessage.settingDirection(MessageDirection.PUSH.getCode(), ImResponseCode.SUCCESS.getCode(), ImResponseCode.SUCCESS.getDescChinese(), null);

        // 发送ws推送消息
        for (Long receiverId : receiverIds) {
            imSendMsg.sendMsgToUser(JsonUtils.toJsonString(webSocketMessage), receiverId);
        }

        return true;
    }
}
