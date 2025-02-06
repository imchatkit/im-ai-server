package com.imai.ws.enums;

import lombok.Getter;

/**
 * 会话类型枚举
 */
@Getter
public enum ConversationType {
    SINGLE(1, "单聊", "Private Chat"),
    GROUP(2, "群聊", "Group Chat"),
    CHATROOM(3, "聊天室", "Chatroom"),
    CHANNEL(4, "频道", "Channel"),
    MILLION_GROUP(5, "万人群聊", "Mass Group Chat"),
    SYSTEM_CONVERSATION(6, "系统对话", "System Conversation"),
    CUSTOM_CONVERSATION(7, "自定义对话", "Custom Conversation");

    private final int code;
    private final String descChinese;
    private final String descEnglish;

    ConversationType(int code, String descChinese, String descEnglish) {
        this.code = code;
        this.descChinese = descChinese;
        this.descEnglish = descEnglish;
    }
} 