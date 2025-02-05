package com.imai.ws;

import lombok.Data;

/**
 * 引用消息
 */
@Data
public class Quote {
    private String msgId;       // 原消息ID
    private String text;        // 原消息内容
} 