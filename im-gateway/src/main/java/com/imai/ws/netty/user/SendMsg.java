package com.imai.ws.netty.user;

import com.imai.ws.netty.config.ChannelAttributes;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author wei
 * @description: 向channel发送消息数据
 * @date 2024/11/4 15:52
 */
@Component
@Slf4j
public class SendMsg {

    @Autowired
    private ChannelUserHolder channelUserHolder;


    public  void write(String message, Channel channel) {
        Long userId = channel.attr(ChannelAttributes.USER_ID).get();
        String device = channel.attr(ChannelAttributes.DEVICE_TYPE).get();
        String channelId = channel.id().asLongText();

        channel.writeAndFlush(new TextWebSocketFrame(message)).addListener(future -> {
            if (future.isSuccess()) {
                log.info("[ws_write_ok]-userId:{},channelId:{},device:{},\n-data:{}", userId, channelId, device, message);
            } else {
                log.error("[ws_write_error]-userId:{},channelId:{},device:{},\n-data:{}", userId, channelId, device, message);
            }
        });
    }

    /**
     * 向某个user所有端发送消息
     *
     * @param userId
     */
    public void toUserAllChannel(Long userId, String message) {

        Set<String> channelsByUserId = channelUserHolder.getChannelsByUserId(userId);

        log.info("[toAllChannel_userId]:{}, channels:{}", userId, channelsByUserId);
        for (String channel : channelsByUserId) {

            //  向channel发送消息
            write(message, channelUserHolder.getChannelByChannelId(channel));

        }
    }

    //     toChannelId
    public void toChannelId(String channelId, String message) {
        Channel channel = channelUserHolder.getChannelByChannelId(channelId);
        if (channel != null) {
            write(message, channel);
        }
    }

    /**
     * 仅向用户某个deviceType发送消息
     */
    public void toDeviceType(Long userId, String deviceType, String message) {
        Set<String> channelsByUserId = channelUserHolder.getChannelsByUserId(userId);

        for (String channel : channelsByUserId) {
            //  向channel发送消息
            Channel byChannelId = channelUserHolder.getChannelByChannelId(channel);
            if (byChannelId.attr(ChannelAttributes.DEVICE_TYPE).get().equals(deviceType)) {
                write(message, byChannelId);
            }
        }
    }
}
