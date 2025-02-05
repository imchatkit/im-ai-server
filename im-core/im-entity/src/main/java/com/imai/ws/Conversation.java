package com.imai.ws;

import lombok.Data;

/**
 * 会话配置
 */
@Data
public class Conversation {
    private Boolean top;            // 是否置顶会话
    private Boolean mute;           // 是否免打扰
    private Boolean unread;         // 是否计入未读
} 