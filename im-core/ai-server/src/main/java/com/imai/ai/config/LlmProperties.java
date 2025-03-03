package com.imai.ai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 大模型配置
 */
@Component
@ConfigurationProperties(prefix = "im")
@Data
public class LlmProperties {
    private String appKey;
}
