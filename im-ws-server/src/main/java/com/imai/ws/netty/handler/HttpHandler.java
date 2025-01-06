package com.imai.ws.netty.handler;


import com.imai.ws.netty.ChannelUserHolder;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Duration;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Slf4j
@Component
@ChannelHandler.Sharable
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

//    @Autowired
//    Algorithm algorithm;
    @Autowired
    private ChannelUserHolder channelUserHolder;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) {
        log.info("---nettyHttpRequest: {}", request);
//        log.info("收到 HTTP 请求: {}", request.uri());
        // 从请求路径问号参数中获取指定参数名

        // 从请求路径中获取参数
//        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.uri());
//        Map<String, List<String>> parameters = queryStringDecoder.parameters();
//
//        // 指定要获取的参数名
//        String paramName = "token";
//        // 获取指定参数名的值
//        String token = parameters.get(paramName).iterator().next();

        String token = request.headers().get("token");

        String deviceType = request.headers().get("deviceType");

        //  校验用户token
        if (token == null || token.isEmpty()) {
//            log.info("token:null");

            // 如果token为空，则返回错误响应
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.UNAUTHORIZED);
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
            response.content().writeBytes("Unauthorized".getBytes(CharsetUtil.UTF_8));
            response.content().writeCharSequence("token is empty", CharsetUtil.UTF_8);
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
            return;
        } else {

//            // 创建JWT验证器
//            JWTVerifier verifier = JWT.require(algorithm).acceptExpiresAt(Duration.ofDays(30).toMillis()).build();
//            try {
//                // 获取token字符串，并去除前缀
//                String tokenString = StringUtils.replace(token, "Bearer ", "");
//                // 验证token
//                DecodedJWT jwtToken = verifier.verify(tokenString);
//                // 获取token的发行者
//                String issuer = jwtToken.getIssuer();
//                // 如果发行者为空，则返回未授权
//                if (isEmpty(issuer)) {
////                    return commonResult(exchange, HttpStatus.UNAUTHORIZED, "Token不合法");
//                    return;
//                }
//                Long userId = jwtToken.getClaim("login_user_id").asLong();
//
//                channelUserHolder.addChannel(userId, ctx.channel(), deviceType);
//
////                    .header("RC-User-Id", jwtToken.getClaim("login_user_id").toString())
////                    .header("RC-User-Name", jwtToken.getClaim("username").asString())
////                    .header("RC-Org-Id", jwtToken.getClaim("org_id").asString())
////                    .header("RC-Tenant-Id", jwtToken.getClaim("tenant_id").asString())
//            } catch (JWTVerificationException e) {
//                log.warn("token 校验失败", e);
////                return commonResult(exchange, HttpStatus.UNAUTHORIZED, "Token校验失败");
//                return;
//            }

            // isWebSocketUpgradeRequest方法检查传入的请求是否是WebSocket升级请求。如果是，处理程序会跳过对该请求的处理，
            // 让后续的管道中的WebSocketServerProtocolHandler处理该请求。如果是普通的HTTP请求，则继续处理请求。
            // 这种方法应该有助于确保WebSocket升级请求不会被HTTP处理程序提前消耗，从而确保WebSocket连接能够成功建立。
            if (isWebSocketUpgradeRequest(request)) {
                // 跳过处理WebSocket升级请求
                ctx.fireChannelRead(request.retain());
            } else {
                handleHttpRequest(ctx, request);
            }
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
