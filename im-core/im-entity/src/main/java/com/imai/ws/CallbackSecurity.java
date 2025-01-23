package com.imai.ws;

import lombok.Data;

/**
 * 回调安全配置
 */
@Data
public class CallbackSecurity {
    private String token;          // 回调验证token
    private String signType;       // 签名类型
} 