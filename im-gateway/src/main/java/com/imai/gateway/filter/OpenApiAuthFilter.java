package com.imai.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.imai.ws.config.ImProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OpenApiAuthFilter implements GlobalFilter, Ordered {

    private final ImProperties imProperties;
    private static final String APP_KEY_HEADER = "X-App-Key";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 检查请求路径是否以 /openapi 开头
        String path = request.getPath().toString();
        if (!path.startsWith("/imcore/openapi/")) {
            return chain.filter(exchange);
        }

        // 获取并验证 AppKey
        String appKey = request.getHeaders().getFirst(APP_KEY_HEADER);
        if (StringUtils.isEmpty(appKey) || !appKey.equals(imProperties.getAppKey())) {
            return handleUnauthorized(exchange, "无效的AppKey");
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    private Mono<Void> handleUnauthorized(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);

        // 返回JSON格式的错误信息
        Map<String, Object> result = new HashMap<>();
        result.put("code", HttpStatus.UNAUTHORIZED.value());
        result.put("msg", message);

        byte[] bytes = JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        return response.writeWith(Mono.just(buffer));
    }
}
