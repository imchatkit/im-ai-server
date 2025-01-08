package com.imai.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * 核心服务
 *
 * @author wei
 */
@SpringBootApplication
public class ImCoreServerApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ImCoreServerApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("(♥◠‿◠)ﾉﾞ  ImCoreServer启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}
