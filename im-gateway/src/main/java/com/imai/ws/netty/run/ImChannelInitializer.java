package com.imai.ws.netty.run;

import com.imai.ws.netty.handler.ImHttpHandler;
import com.imai.ws.netty.handler.ImHeartbeatHandler;
import com.imai.ws.netty.handler.ImWsMsgHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ImChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    private static final int READ_IDLE_TIME = 60; // 读空闲时间
    private static final int WRITE_IDLE_TIME = 0;  // 写空闲时间
    private static final int ALL_IDLE_TIME = 0;    // 读写空闲时间

    @Resource
    private ImWsMsgHandler imWsMsgHandler;
    @Resource
    private ImHttpHandler imHttpHandler;
    @Resource
    private ImHeartbeatHandler imHeartbeatHandler;

    @Override
    protected void initChannel(NioSocketChannel channel) throws Exception {
        // HTTP 编解码
        channel.pipeline().addLast(new HttpServerCodec());
        channel.pipeline().addLast(new HttpObjectAggregator(65536));
        channel.pipeline().addLast(new ChunkedWriteHandler());
        
        // 认证处理
        channel.pipeline().addLast(imHttpHandler);
        
        // WebSocket 协议处理
        channel.pipeline().addLast(new WebSocketServerCompressionHandler());
        channel.pipeline().addLast(new WebSocketServerProtocolHandler("/ws", null, true, 65536, false, true));

        // 心跳检测
        channel.pipeline().addLast(new IdleStateHandler(READ_IDLE_TIME, WRITE_IDLE_TIME, ALL_IDLE_TIME, TimeUnit.SECONDS));
        channel.pipeline().addLast(imHeartbeatHandler);

        // 消息处理
        channel.pipeline().addLast(imWsMsgHandler);
    }
}
