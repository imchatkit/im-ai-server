package com.imai.ws.filter;//package com.recircle.gateway.filter;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//import java.util.List;
//
//import static org.apache.commons.lang3.StringUtils.isEmpty;
//
//
/// **
// * Token认证过滤器，用于全局过滤请求，验证Token合法性
// * 实现了GlobalFilter和Ordered接口，以支持全局过滤和排序执行
// *
// * @author
// * @date
// */
//
//@Slf4j
//@Component
//public class TokenAuthFilter implements GlobalFilter, Ordered {
//
//    @Autowired
//    Algorithm algorithm;
//
/// /    @Autowired
/// /    AuthProperties ignoreFilter;
//
//    /**
//     * 过滤函数，对每个请求进行Token验证
//     *
//     * @param exchange 服务器Web交换对象，包含请求和响应
//     * @param chain    过滤链，用于将控制权转交给下一个过滤器
//     * @return Mono<Void> 异步处理结果
//     */
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        // 获取请求头中的token
//        List<String> token = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
//
//        // 获取请求的uri
//        String uri = exchange.getRequest().getURI().getPath();
//        // 如果uri在忽略列表中，则直接放行
////        if (ignoreFilter.getIgnoreUrls().contains(uri)) {
////            return chain.filter(exchange);
////            // 如果请求头中没有token，则返回未授权错误
////        } else if (CollectionUtils.isEmpty(token)) {
////            return unauthorized(exchange);
////        }
//
//        // 创建JWT验证器
//        JWTVerifier verifier = JWT.require(algorithm).acceptExpiresAt(Duration.ofDays(30).toMillis()).build();
//        try {
//            // 获取token字符串，并去除前缀
//            String tokenString = StringUtils.replace(token.get(0), "Bearer ", "");
//            // 验证token
//            DecodedJWT jwtToken = verifier.verify(tokenString);
//            // 获取token的发行者
//            String issuer = jwtToken.getIssuer();
//            // 如果发行者为空，则返回未授权
//            if (isEmpty(issuer)) {
//                return commonResult(exchange, HttpStatus.UNAUTHORIZED, "Token不合法");
//            }
//
//        } catch (JWTVerificationException e) {
//            log.warn("token 校验失败", e);
//            return commonResult(exchange, HttpStatus.UNAUTHORIZED, "Token校验失败");
//        }
//    }
//
//    /**
//     * 处理通用API请求的函数
//     *
//     * @param exchange 服务器Web交换对象
//     * @param chain    过滤链
//     * @param jwtToken 解码后的JWT Token
//     * @return Mono<Void> 异步处理结果
//     */
//    private Mono<Void> forwardCommonApi(ServerWebExchange exchange, GatewayFilterChain chain, DecodedJWT jwtToken) {
//        // 构造新的ServerWebExchange，添加用户id和用户名的header
//        ServerWebExchange newExchange = exchange.mutate().request(builder -> builder
//            .header("RC-User-Id", jwtToken.getClaim("login_user_id").toString())
//            .header("RC-User-Name", jwtToken.getClaim("username").asString())
//        ).build();
//        return chain.filter(newExchange);
//    }
//
//
//
//    private Mono<Void> unauthorized(ServerWebExchange exchange) {
//        // 返回未授权
//        return commonResult(exchange, HttpStatus.UNAUTHORIZED, "请求未授权");
//    }
//
//    private Mono<Void> forbidden(ServerWebExchange exchange) {
//        // 返回拒绝访问
//        return commonResult(exchange, HttpStatus.FORBIDDEN, "拒绝访问");
//    }
//
//    private Mono<Void> commonResult(ServerWebExchange exchange, HttpStatus status, String msg) {
//        // 设置响应状态码和内容类型
//        exchange.getResponse().setStatusCode(status);
//        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
////        CommonResult<Object> result = CommonResult.error(status.value(), msg);
//        // 构造响应内容
//        byte[] jsonByte = "ERROR".getBytes();
//        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(jsonByte);
//        // 返回响应
//        return exchange.getResponse().writeWith(Mono.just(buffer));
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
