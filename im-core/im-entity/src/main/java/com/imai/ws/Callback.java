package com.imai.ws;

import lombok.Data;
import java.util.List;

/**
 * 回调控制
 */
@Data
public class Callback {
    private Boolean enable=false;         // 是否开启回调
    private String url;            // 回调地址
    private List<String> events;    // 需要回调的事件类型
    private Integer timeout=5000;        // 回调超时时间(毫秒)

    private CallbackRetry retry;    // 重试配置
    private CallbackSecurity security;  // 回调安全配置
} 