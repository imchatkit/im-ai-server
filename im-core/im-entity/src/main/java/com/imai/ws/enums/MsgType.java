package com.imai.ws.enums;

import lombok.Getter;

/**
 * 消息类型枚举
 */
@Getter
public enum MsgType {
    // === 基础消息 ===
    TEXT(1, "文本消息"),
    IMAGE(2, "图片消息"),
    AUDIO(3, "语音消息"),
    VIDEO(4, "视频消息"),
    FILE(5, "文件消息"),
    LOCATION(6, "位置消息"),
    CONTACT(7, "名片消息"),
    
    // === 富媒体消息 ===
    RICH_TEXT(11, "富文本消息"),
    MARKDOWN(12, "Markdown消息"),
    HTML(13, "HTML消息"),
    
    // === 复合消息 ===
    QUOTE(21, "引用消息"),
    FORWARD(22, "转发消息"),
    MERGE(23, "合并消息"),
    CARD(24, "卡片消息"),
    
    // === 互动消息 ===
    REACTION(31, "表情回应"),
    VOTE(32, "投票消息"),
    RED_PACKET(33, "红包消息"),
    GAME(34, "小游戏消息"),
    
    // === 通知消息 ===
    GROUP_NOTIFY(41, "群通知"),
    FRIEND_NOTIFY(42, "好友通知"),
    SYSTEM_NOTIFY(43, "系统通知"),
    CUSTOM_NOTIFY(44, "自定义通知"),
    
    // === 状态消息 ===
    TYPING(51, "正在输入"),
    TYPING_END(52, "结束输入"),
    
    // === WebRTC信令类型 ===
    WEBRTC_SIGNAL(61, "音视频信令"),
    WEBRTC_STATUS(62, "通话状态"),
    WEBRTC_QUALITY(63, "质量数据"),
    
    // === 屏幕共享类型 ===
    SCREEN_SHARE(71, "屏幕共享"),
    SCREEN_CONTROL(72, "共享控制"),
    
    // === 自定义消息 ===
    CUSTOM(91, "自定义消息"),
    TEMPLATE(92, "模板消息");

    private final int code;
    private final String desc;

    MsgType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
} 