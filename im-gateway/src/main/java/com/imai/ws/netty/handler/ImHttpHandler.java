package com.imai.ws.netty.handler;

import cn.dev33.satoken.stp.StpUtil;
import com.imai.ws.netty.user.ChannelUserHolder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.enums.UserType;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@ChannelHandler.Sharable
public class ImHttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Autowired
    private ChannelUserHolder channelUserHolder;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) {
        try {
            log.info("---nettyHttpRequest: {}", request);

            // 如果不是WebSocket握手请求，返回错误
            if (!isWebSocketUpgradeRequest(request)) {
                sendHttpResponse(ctx, request, new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.BAD_REQUEST,
                    Unpooled.copiedBuffer("非WebSocket请求", CharsetUtil.UTF_8)
                ));
                return;
            }

            String token = request.headers().get("token");
            String deviceType = request.headers().get("deviceType");

            // 从URL中获取参数
            QueryStringDecoder queryDecoder = new QueryStringDecoder(request.uri());
            if (token == null && queryDecoder.parameters().containsKey("token")) {
                token = URLDecoder.decode(
                    queryDecoder.parameters().get("token").get(0),
                    StandardCharsets.UTF_8
                );
            }
            if (deviceType == null && queryDecoder.parameters().containsKey("deviceType")) {
                deviceType = queryDecoder.parameters().get("deviceType").get(0);
            }

            // 校验用户token
            if (token == null || token.isEmpty()) {
                sendHttpResponse(ctx, request, new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.UNAUTHORIZED,
                    Unpooled.copiedBuffer("Unauthorized: token is empty", CharsetUtil.UTF_8)
                ));
                return;
            }

            // 验证token
            Object loginId = StpUtil.getLoginIdByToken(token);
            if (loginId == null) {
                sendHttpResponse(ctx, request, new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.UNAUTHORIZED,
                    Unpooled.copiedBuffer("Unauthorized: invalid token", CharsetUtil.UTF_8)
                ));
                return;
            }

            // 获取登录用户信息
            LoginUser loginUser = LoginHelper.getLoginUser(token);
            if (loginUser == null || !UserType.IM_USER.getUserType().equals(loginUser.getUserType())) {
                sendHttpResponse(ctx, request, new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.UNAUTHORIZED,
                    Unpooled.copiedBuffer("Unauthorized: invalid user type", CharsetUtil.UTF_8)
                ));
                return;
            }

            // 添加到channel管理器
            channelUserHolder.addChannel(loginUser.getUserId(), ctx.channel(), deviceType);

            // 验证通过，继续处理WebSocket握手
            ctx.fireChannelRead(request.retain());

        } catch (Exception e) {
            log.error("Token验证失败: {}", e.getMessage(), e);
            sendHttpResponse(ctx, request, new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.UNAUTHORIZED,
                Unpooled.copiedBuffer("Unauthorized: " + e.getMessage(), CharsetUtil.UTF_8)
            ));
        }
    }

    private boolean isWebSocketUpgradeRequest(FullHttpRequest request) {
        HttpHeaders headers = request.headers();
        return "websocket".equalsIgnoreCase(headers.get(HttpHeaderNames.UPGRADE)) &&
               "Upgrade".equalsIgnoreCase(headers.get(HttpHeaderNames.CONNECTION));
    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
        res.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        HttpUtil.setContentLength(res, res.content().readableBytes());

        // 如果是非法的WebSocket请求，需要关闭连接
        boolean keepAlive = HttpUtil.isKeepAlive(req) && res.status().code() == 200;
        if (!keepAlive) {
            res.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
        } else {
            res.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        if (!keepAlive || res.status().code() != 200) {
            ctx.writeAndFlush(res).addListener(ChannelFutureListener.CLOSE);
        } else {
            ctx.writeAndFlush(res);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("HTTP处理异常: ", cause);
        ctx.close();
    }
}
