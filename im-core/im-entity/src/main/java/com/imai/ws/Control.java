package com.imai.ws;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
/**
 * 控制信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Control {
    private Boolean persistent = true;     // 是否持久化
    private Boolean syncDevice = true;     // 是否多端同步

    private Boolean needReceipt = true;    // 是否需要回执

    private Push push;             // 推送控制
    private Security security;     // 安全控制
    private Callback callback;     // 回调控制
}
