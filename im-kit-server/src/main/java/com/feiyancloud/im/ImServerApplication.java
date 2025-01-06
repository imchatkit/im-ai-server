package com.feiyancloud.im;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * 演示模块
 *
 * @author Lion Li
 */
@SpringBootApplication
public class ImServerApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ImServerApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("(♥◠‿◠)ﾉﾞ  ImServer启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}
