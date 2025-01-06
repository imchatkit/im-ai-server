package com.imai.ws.netty;

import com.imai.ws.netty.config.NettyConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NettyServer {

    @Resource
    private NettyConfig nettyConfig;

    @Resource
    private MyChannelInitializer myChannelInitializer;

    public void start() throws Exception {
        int port = nettyConfig.getPort();

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(myChannelInitializer)
                // TCP参数优化
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.SO_RCVBUF, 8192)
                .childOption(ChannelOption.SO_SNDBUF, 8192);

            ChannelFuture f = b.bind(port).sync();
            log.info("Netty Server start on port: {}", port);
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
