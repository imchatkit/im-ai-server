package com.imai.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * WS消息处理线程池配置
 */
@Configuration
public class ThreadPoolConfig {

    @Bean("msgProcessExecutor")
    public ThreadPoolTaskExecutor msgProcessExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors() * 2);                 // 核心线程数
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 3);                  // 最大线程数
        executor.setQueueCapacity(1000);              // 队列容量
        executor.setKeepAliveSeconds(300);            // 线程空闲时间
        executor.setThreadNamePrefix("WS_MSG-process-"); // 线程名前缀
        // 拒绝策略：由调用线程处理
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
} 