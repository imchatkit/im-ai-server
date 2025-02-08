package com.imai.ws.netty.handler;


import com.imai.handler.filter.ImMsgFilterHandler;
import com.imai.ws.netty.user.ChannelUserHolder;
import com.imai.ws.netty.user.SendMsgUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: wei
 * @Date: 2024/3/13 自定义handler
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class ImWsMsgHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    @Resource
    private SendMsgUtil sendMsgUtil;

    @Resource
    private ChannelUserHolder channelUserHolder;

    @DubboReference
    private ImMsgFilterHandler imMsgFilterHandler;

    @Resource
    private ThreadPoolTaskExecutor msgProcessExecutor;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        String channelId = ctx.channel().id().asLongText();
        Long userId = channelUserHolder.getUserIdByChannel(ctx.channel());
        if (userId == null) {
            log.warn("用户未登录，channelId: {}", channelId);
            sendMsgUtil.send("用户未登录", ctx.channel());
            return;
        }

        // 在主线程中获取消息内容
        String messageContent = msg.text();

        // 使用线程池异步处理消息
        try {
            msgProcessExecutor.execute(() -> {

                // 在新线程中处理消息
            boolean result = imMsgFilterHandler.filter(messageContent, userId, channelId);
            if (result) {
                log.info("消息处理成功, userId:{}, channelId:{}, msg:{}", userId, channelId, messageContent);
            } else {
                    sendMsgUtil.send("res:error:" + messageContent, ctx.channel());
                }
            });
        } catch (Exception e) {
            log.error("消息处理异常, userId:{}, channelId:{}, msg:{}", userId, channelId, messageContent, e);
            sendMsgUtil.send("res:error:" + messageContent, ctx.channel());
        }
    }

}
