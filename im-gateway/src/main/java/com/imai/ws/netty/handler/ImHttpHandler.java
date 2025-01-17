package com.imai.ws.netty.handler;


import cn.dev33.satoken.stp.StpUtil;
import com.imai.ws.netty.user.ChannelUserHolder;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.enums.UserType;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ChannelHandler.Sharable
public class ImHttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Autowired
    private ChannelUserHolder channelUserHolder;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) {
        log.info("---nettyHttpRequest: {}", request);

        String token = request.headers().get("token");
        String deviceType = request.headers().get("deviceType");

        // 校验用户token
        if (token == null || token.isEmpty()) {
            // 如果token为空，则返回错误响应
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.UNAUTHORIZED);
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
            response.content().writeBytes("Unauthorized: token is empty".getBytes(CharsetUtil.UTF_8));
            ctx.writeAndFlush(response);
            return;
        }

        try {
            // 去除 Bearer 前缀
            String tokenValue = token.replace("Bearer ", "");
            // 验证token
            Object loginId = StpUtil.getLoginIdByToken(tokenValue);
            if (loginId == null) {
                // 如果token为空，则返回错误响应
                FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.UNAUTHORIZED);
                response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
                response.content().writeBytes("Unauthorized: token is empty".getBytes(CharsetUtil.UTF_8));
                ctx.writeAndFlush(response);
                throw new RuntimeException("Token不合法");
            }

            // 获取登录用户信息
            LoginUser loginUser = LoginHelper.getLoginUser(tokenValue);
            if (loginUser == null || !UserType.IM_USER.getUserType().equals(loginUser.getUserType())) {
                // 如果token为空，则返回错误响应
                FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.UNAUTHORIZED);
                response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
                response.content().writeBytes("Unauthorized: token is empty".getBytes(CharsetUtil.UTF_8));
                ctx.writeAndFlush(response);
                throw new RuntimeException("非法的用户类型");
            }

            // 添加到channel管理器
            channelUserHolder.addChannel(loginUser.getUserId(), ctx.channel(), deviceType);

            // isWebSocketUpgradeRequest方法检查传入的请求是否是WebSocket升级请求
            if (isWebSocketUpgradeRequest(request)) {
                // 跳过处理WebSocket升级请求
                ctx.fireChannelRead(request.retain());
            } else {
                handleHttpRequest(ctx, request);
            }

        } catch (Exception e) {
            log.warn("token 校验失败", e);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.UNAUTHORIZED);
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
            response.content().writeBytes(("Unauthorized: " + e.getMessage()).getBytes(CharsetUtil.UTF_8));
            ctx.writeAndFlush(response);
            return;
        }
    }

    private boolean isWebSocketUpgradeRequest(FullHttpRequest request) {
        HttpHeaders headers = request.headers();
        return "websocket".equalsIgnoreCase(headers.get("Upgrade")) && "Upgrade".equalsIgnoreCase(headers.get("Connection"));
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        try {
            // 创建HTTP响应
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer("你好，世界！", CharsetUtil.UTF_8));
            response.headers().set("Content-Type", "text/plain");
            response.headers().set("Content-Length", response.content().readableBytes());
            // 发送响应
            ctx.writeAndFlush(response).addListener(future -> {
                if (!future.isSuccess()) {
                    log.error("发送HTTP响应时发生异常: ", future.cause());
                }
            });
        } catch (Exception e) {
            log.error("处理HTTP请求时发生异常: ", e);
            ctx.close();
        } finally {
            // 释放请求对象
            ReferenceCountUtil.release(request);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("处理 HTTP 请求时发生异常: ", cause);
        ctx.close();
    }

}
