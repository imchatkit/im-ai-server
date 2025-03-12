package com.imai.ws;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 回调安全配置
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallbackSecurity {
    private String token;          // 回调验证token
    private String signType;       // 签名类型
}
