package com.imai.ws;

import lombok.Data;

/**
 * 控制信息
 */
@Data
public class Control {
    private Boolean persistent;     // 是否持久化
    private Boolean syncDevice;     // 是否多端同步
    private Integer priority;       // 消息优先级(0-9)
    private Integer ttl;           // 消息有效期(秒)
    
    private Boolean needReceipt;    // 是否需要回执
    private Integer receiptType;    // 回执类型(1送达/2已读)
    private Integer readTimeout;    // 已读超时时间(秒)
    
    private Push push;             // 推送控制
    private Security security;     // 安全控制
    private Callback callback;     // 回调控制
} 