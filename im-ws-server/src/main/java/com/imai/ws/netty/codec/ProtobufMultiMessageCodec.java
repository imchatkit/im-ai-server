package com.imai.ws.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;

import java.util.List;

/**
 * @author wei
 * @description: 多protobuf类型消息编解码器
 * @author: wei
 * @date 2024/10/27 17:57
 */

public class ProtobufMultiMessageCodec extends MessageToMessageCodec<ByteBuf, MessageLite> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageLite msg, List<Object> out) throws Exception {
        // 根据消息类型编码
//        if (msg instanceof Message1) {
//            out.add(ProtobufEncoder.encode(msg));
//        } else if (msg instanceof Message2) {
//            out.add(ProtobufEncoder.encode(msg));
//        }
        // 可以继续添加其他消息类型的编码
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        // 根据消息类型解码
//        if (buf.getByte(0) == ProtobufMessage.MESSAGE_1) {
//            out.add(ProtobufDecoder.decode(Message1.getDefaultInstance(), buf));
//        } else if (buf.getByte(0) == ProtobufMessage.MESSAGE_2) {
//            out.add(ProtobufDecoder.decode(Message2.getDefaultInstance(), buf));
//        }
        // 可以继续添加其他消息类型的解码
    }
}
