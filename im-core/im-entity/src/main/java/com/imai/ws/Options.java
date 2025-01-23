package com.imai.ws;

import lombok.Data;
import java.util.Map;

/**
 * 可选配置
 */
@Data
public class Options {
    private String bizType;         // 业务类型
    private String bizId;           // 业务ID
    private Conversation conversation;  // 会话配置
    private Map<String, Object> custom;  // 自定义业务字段
    private Map<String, Object> extra;   // 额外扩展字段
} 