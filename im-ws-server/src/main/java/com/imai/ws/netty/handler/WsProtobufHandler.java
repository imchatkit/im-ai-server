package com.imai.ws.netty.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wei
 * @date 2024/10/27 17:59
 */
@Slf4j
@ChannelHandler.Sharable
@Component
public class WsProtobufHandler extends SimpleChannelInboundHandler<MessageLite> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageLite msg) throws Exception {
        log.info("Received MyWsProtobufHandler message: {}", msg);

        // 根据消息类型处理不同的逻辑
//        if (msg instanceof Message1) {
//            Message1 message1 = (Message1) msg;
//            // 处理 Message1 类型的消息逻辑
//            System.out.println("Received Message1: " + message1);
//        } else if (msg instanceof Message2) {
//            Message2 message2 = (Message2) msg;
//            // 处理 Message2 类型的消息逻辑
//            System.out.println("Received Message2: " + message2);
//        } else {
//            // 处理其他类型消息的逻辑
//            System.out.println("Received unknown message type");
//        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
