package com.imai.ws.netty.run;


import com.imai.ws.netty.handler.ImHttpHandler;
import com.imai.ws.netty.handler.HeartbeatHandler;
import com.imai.ws.netty.handler.WsMsgHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    @Resource
    private WsMsgHandler wsMsgHandler;
    @Resource
    private ImHttpHandler imHttpHandler;
    @Resource
    private HeartbeatHandler heartbeatHandler;

    @Override
    protected void initChannel(NioSocketChannel channel) throws Exception {
        channel.pipeline().addLast(new HttpServerCodec());
        channel.pipeline().addLast(new HttpObjectAggregator(65536));
        channel.pipeline().addLast(imHttpHandler);

        // --- 心跳检测 ---
        // 添加 IdleStateHandler 来处理空闲状态事件
//        channel.pipeline().addLast(new IdleStateHandler(READ_IDLE_TIME, WRITE_IDLE_TIME, ALL_IDLE_TIME, TimeUnit.SECONDS));
        // 添加自定义的处理器 通过IdleStateHandler设置了读空闲时间为10秒，写空闲时间为0秒，读写空闲时间为0秒。这表示如果10秒内没有读取到数据，就会触发读空闲事件；如果设置为0，表示对应的空闲检测不会被触发。你可以根据需要调整这些时间间隔来满足你的需求。
        //请确保在Netty的ChannelInitializer中添加IdleStateHandler以便处理空闲状态事件，以及根据具体情况设置合适的空闲时间间隔。
        channel.pipeline().addLast(heartbeatHandler);
        // --- 心跳检测 ---

        // 添加自定义的protobuf编解码器
//        channel.pipeline().addLast(new ProtobufMultiMessageCodec());
        channel.pipeline().addLast(new WebSocketServerProtocolHandler("/ws"));
        channel.pipeline().addLast(wsMsgHandler);


    }
}
