package com.imai.ws.netty.user;

import com.imai.ws.netty.config.ImChannelAttributes;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * WebSocket消息发送服务
 *
 * @author wei
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SendMsgUtil {

    private final ChannelUserHolder channelUserHolder;

    /**
     * 发送消息到指定Channel
     *
     * @param message 消息内容
     * @param channel 目标Channel
     * @return CompletableFuture<Boolean> 发送结果
     */
    public CompletableFuture<Boolean> send(String message, Channel channel) {
        Assert.notNull(channel, "Channel cannot be null");
        Assert.hasText(message, "Message cannot be empty");

        Long userId = channel.attr(ImChannelAttributes.USER_ID).get();
        String device = channel.attr(ImChannelAttributes.DEVICE_TYPE).get();
        String channelId = channel.id().asLongText();

        CompletableFuture<Boolean> future = new CompletableFuture<>();

        try {
            ChannelFuture channelFuture = channel.writeAndFlush(new TextWebSocketFrame(message));
            channelFuture.addListener(result -> {
                boolean success = result.isSuccess();
                if (success) {
                    log.info("[ws_send_ok] Data:{}, userId:{}, channelId:{}, device:{}, message length:{}",
                        message, userId, channelId, device, message.length());
                    log.debug("[ws_send_ok_detail] message:{}", message);
                } else {
                    log.error("[ws_send_error] Data:{}, userId:{}, channelId:{}, device:{}, error:{}",
                        message, userId, channelId, device, result.cause().getMessage());
                }
                future.complete(success);
            });
        } catch (Exception e) {
            log.error("[ws_send_exception] userId:{}, channelId:{}, device:{}, error:{}",
                userId, channelId, device, e.getMessage(), e);
            future.complete(false);
        }

        return future;
    }

    /**
     * 向用户的所有设备发送消息
     *
     * @param userId  用户ID
     * @param message 消息内容
     * @return 发送成功的channel数量
     */
    public long toUserAllChannel(Long userId, String message) {
        Assert.notNull(userId, "UserId cannot be null");
        Assert.hasText(message, "Message cannot be empty");

        Set<String> channelIds = channelUserHolder.getChannelsByUserId(userId);
        if (channelIds.isEmpty()) {
            log.warn("[toAllChannel_empty] userId:{} has no active channels", userId);
            return 0;
        }

        log.info("[toAllChannel_start] userId:{}, channelCount:{}", userId, channelIds.size());

        return channelIds.stream()
            .map(channelUserHolder::getChannelByChannelId)
            .filter(Objects::nonNull)
            .map(channel -> send(message, channel))
            .map(future -> future.join())
            .filter(Boolean::booleanValue)
            .count();
    }

    /**
     * 向指定channelId发送消息
     *
     * @param channelId 目标channelId
     * @param message   消息内容
     * @return 是否发送成功
     */
    public boolean toChannelId(String channelId, String message) {
        Assert.hasText(channelId, "ChannelId cannot be empty");
        Assert.hasText(message, "Message cannot be empty");

        Channel channel = channelUserHolder.getChannelByChannelId(channelId);
        if (channel == null) {
            log.warn("[toChannelId_notFound] channelId:{} not found", channelId);
            return false;
        }

        return send(message, channel).join();
    }

    /**
     * 向用户的指定设备类型发送消息
     *
     * @param userId     用户ID
     * @param deviceType 设备类型
     * @param message    消息内容
     * @return 发送成功的channel数量
     */
    public long toDeviceType(Long userId, String deviceType, String message) {
        Assert.notNull(userId, "UserId cannot be null");
        Assert.hasText(deviceType, "DeviceType cannot be empty");
        Assert.hasText(message, "Message cannot be empty");

        Set<String> channelIds = channelUserHolder.getChannelsByUserId(userId);
        if (channelIds.isEmpty()) {
            log.warn("[toDeviceType_empty] userId:{}, deviceType:{} has no active channels", userId, deviceType);
            return 0;
        }

        log.info("[toDeviceType_start] userId:{}, deviceType:{}, totalChannels:{}", userId, deviceType, channelIds.size());

        return channelIds.stream()
            .map(channelUserHolder::getChannelByChannelId)
            .filter(Objects::nonNull)
            .filter(channel -> deviceType.equals(channel.attr(ImChannelAttributes.DEVICE_TYPE).get()))
            .map(channel -> send(message, channel))
            .map(future -> future.join())
            .filter(Boolean::booleanValue)
            .count();
    }
}
