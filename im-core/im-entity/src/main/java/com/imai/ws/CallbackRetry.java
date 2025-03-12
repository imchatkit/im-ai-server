package com.imai.ws;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 回调重试配置
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallbackRetry {
    private Integer maxTimes;       // 最大重试次数
    private Integer interval;       // 重试间隔(毫秒)
}
