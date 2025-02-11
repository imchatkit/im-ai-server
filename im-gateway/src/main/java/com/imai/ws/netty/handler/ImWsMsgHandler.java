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
 * ImWsMsgHandler 类是 Netty WebSocket 服务器中的消息处理器。
 * <p>
 * 它的主要职责是接收客户端通过 WebSocket 连接发送的 TextWebSocketFrame 消息，
 * 并将这些消息传递给消息过滤器 ImMsgFilterHandler 进行处理。
 * <p>
 * 该类使用了以下关键组件：
 * 1. SendMsgUtil: 用于向客户端发送消息。
 * 2. ChannelUserHolder: 用于管理 Channel 和 User 之间的关系，
 *    维护用户的登录状态。
 * 3. ImMsgFilterHandler: 消息过滤器，用于对消息内容进行过滤和处理。
 * 4. ThreadPoolTaskExecutor: 线程池，用于异步处理消息，避免阻塞 Netty 的主线程。
 * <p>
 * 该类使用了 @Slf4j 注解，用于生成 log 对象，方便进行日志记录。
 * 使用了 @Component 注解，将该类注册为 Spring Bean，方便进行依赖注入。
 * 使用了 @ChannelHandler.Sharable 注解，表示该 Handler 可以在多个 Channel 之间共享。
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

    /**
     * 接收 WebSocket 消息，并将其传递给消息过滤器进行处理。
     * <p>
     * 该方法是 Netty 框架中的回调方法，当接收到客户端发送的 TextWebSocketFrame 消息时，
     * 该方法会被自动调用。
     * <p>
     * 方法的参数：
     * 1. ctx: ChannelHandlerContext 对象，表示 ChannelHandler 的上下文，
     *    可以通过该对象获取 Channel、EventLoop 等信息。
     * 2. msg: TextWebSocketFrame 对象，表示接收到的 WebSocket 消息。
     * <p>
     * 方法的处理步骤：
     * 1. 获取 channelId。
     * 2. 获取 userId。
     * 3. 检查用户是否登录。
     * 4. 获取消息内容。
     * 5. 使用线程池异步处理消息。
     * @param ctx ChannelHandlerContext
     * @param msg TextWebSocketFrame
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        // 获取当前连接的 channelId
        String channelId = ctx.channel().id().asLongText();
        
        // 通过 ChannelUserHolder 获取当前连接对应的 userId
        Long userId = channelUserHolder.getUserIdByChannel(ctx.channel());
        
        // 检查用户是否已登录
        if (userId == null) {
            log.warn("用户未登录，channelId: {}", channelId);
            sendMsgUtil.send("用户未登录", ctx.channel());
            return;
        }

        // 在主线程中获取 WebSocket 消息内容
        String messageContent = msg.text();

        // 使用线程池异步处理消息，避免阻塞 Netty 的 I/O 线程
        try {
            msgProcessExecutor.execute(() -> {
                // 在新线程中调用消息过滤器处理消息
                boolean result = imMsgFilterHandler.filter(messageContent, userId, channelId);
                
                // 根据过滤结果进行相应处理
                if (result) {
                    log.info("消息处理成功, userId:{}, channelId:{}, msg:{}", userId, channelId, messageContent);
                } else {
                    // 如果消息被过滤，向客户端发送错误响应
                    sendMsgUtil.send("res:error:" + messageContent, ctx.channel());
                }
            });
        } catch (Exception e) {
            log.error("消息处理异常, userId:{}, channelId:{}, msg:{}", userId, channelId, messageContent, e);
            sendMsgUtil.send("res:error:" + messageContent, ctx.channel());
        }
    }

}
