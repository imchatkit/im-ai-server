package com.imai.ws;

import lombok.Data;

/**
 * 安全控制
 */
@Data
public class Security {
    private Boolean checkSensitive;  // 是否检查敏感词
    private Boolean checkSpam;       // 是否检查垃圾消息
    private Boolean encryption;      // 是否加密
    private String sign;            // 消息签名
} 