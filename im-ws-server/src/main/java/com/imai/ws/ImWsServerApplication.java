package com.imai.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * ws网关模块
 *
 * @author wei
 */
@SpringBootApplication
public class ImWsServerApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ImWsServerApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("(♥◠‿◠)ﾉﾞ  ImNettyServer启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}
