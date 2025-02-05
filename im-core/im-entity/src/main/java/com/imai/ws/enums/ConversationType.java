package com.imai.ws.enums;

import lombok.Getter;

/**
 * 会话类型枚举
 */
@Getter
public enum ConversationType {
    SINGLE(1, "单聊"),
    GROUP(2, "群聊"),
    CHATROOM(3, "聊天室"),
    CHANNEL(4, "频道"),
    MILLION_GROUP(5, "万人群聊"),
    SYSTEM_CONVERSATION(6, "系统对话"),
    CUSTOM_CONVERSATION(7, "自定义对话");

    private final int code;
    private final String desc;

    ConversationType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
} 