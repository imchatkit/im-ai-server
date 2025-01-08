package com.imai.ws.netty.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class NettyConfig {

    @Value("${netty.port}")
    private int port;
}
