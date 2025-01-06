package com.imai.ws.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * ws心跳检测
 *
 * @author wei
 * @date 2024/10/27 15:50
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class WebSocketHeartbeatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static final HashedWheelTimer WHEEL_TIMER = new HashedWheelTimer();
    private static final int BEAT_INTERVAL = 55;
    private static final String PING_MSG = "ping";
    private static final String PONG_MSG = "pong";
    private final AttributeKey<Timeout> timeoutKey = AttributeKey.valueOf("timeout");
    @Resource
    private ChannelUserHolder channelUserHolder;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Long userId = ctx.channel().attr(ChannelAttributes.USER_ID).get();
        String device = ctx.channel().attr(ChannelAttributes.DEVICE_TYPE).get();
        String channelId = ctx.channel().id().asLongText();
        log.info("[WsConnected],userId:{},device:{},channelId:{}", userId, device, channelId);
        startPingScheduler(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        Long userId = ctx.channel().attr(ChannelAttributes.USER_ID).get();
        String device = ctx.channel().attr(ChannelAttributes.DEVICE_TYPE).get();
        String channelId = ctx.channel().id().asLongText();

        String message = msg.text();
        if (message.equals(PONG_MSG)) {
            log.info("[RecPong]: {},userId:{},device:{},channelId:{}", msg.text(), userId, device, channelId);
            return;
        }
        if (message.equals(PING_MSG)) {
            log.info("[resPong]: {},userId:{},device:{},channelId:{}", msg.text(), userId, device, channelId);
            pong(ctx);
            return;
        }
        cancelTimeout(ctx.channel());
        startPingScheduler(ctx.channel());

        ctx.fireChannelRead(msg.retain());
    }

    private static void pong(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(new TextWebSocketFrame(PONG_MSG));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Long userId = ctx.channel().attr(ChannelAttributes.USER_ID).get();
        String device = ctx.channel().attr(ChannelAttributes.DEVICE_TYPE).get();
        String channelId = ctx.channel().id().asLongText();

        channelUserHolder.removeChannel(ctx.channel());
        log.info("[WsClosed] client userId:{},device:{},channelId:{}", userId, device, channelId);
        cancelTimeout(ctx.channel());
    }

    private void startPingScheduler(Channel channel) {
        TimerTask task = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                channel.writeAndFlush(new TextWebSocketFrame(PING_MSG));
                startTimeout(channel);
            }
        };
        Timeout timeout = WHEEL_TIMER.newTimeout(task, BEAT_INTERVAL, TimeUnit.SECONDS);
        channel.attr(timeoutKey).set(timeout);
    }

    private void startTimeout(Channel channel) {
        Long userId = channel.attr(ChannelAttributes.USER_ID).get();
        String device = channel.attr(ChannelAttributes.DEVICE_TYPE).get();
        String channelId = channel.id().asLongText();
        TimerTask task = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {

                log.info(
                    "[wsPongTimeout] Did not receive pong message, closing connection,userId:{},device:{},channelId:{}",
                    userId, device, channelId);
//                channel.close();
            }
        };
        Timeout timeout = WHEEL_TIMER.newTimeout(task, BEAT_INTERVAL, TimeUnit.SECONDS);
        channel.attr(timeoutKey).set(timeout);
    }

    private void cancelTimeout(Channel channel) {
        Timeout timeout = channel.attr(timeoutKey).get();
        if (timeout != null) {
            timeout.cancel();
        }
    }
}
