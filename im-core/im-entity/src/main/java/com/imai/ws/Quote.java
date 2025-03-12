package com.imai.ws;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
/**
 * 引用消息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quote {
    private String msgId;       // 原消息ID
    private String text;        // 原消息内容
}
