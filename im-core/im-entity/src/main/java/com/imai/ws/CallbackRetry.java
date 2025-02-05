package com.imai.ws;

import lombok.Data;

/**
 * 回调重试配置
 */
@Data
public class CallbackRetry {
    private Integer maxTimes;       // 最大重试次数
    private Integer interval;       // 重试间隔(毫秒)
} 