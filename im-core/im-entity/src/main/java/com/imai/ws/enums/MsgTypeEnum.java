package com.imai.ws.enums;

import lombok.Getter;

/**
 * 消息类型枚举
 */
@Getter
public enum MsgTypeEnum {
    // === 基础消息 ===
    TEXT(1, "文本消息", "Text Message"),
    IMAGE(2, "图片消息", "Image Message"),
    AUDIO(3, "语音消息", "Audio Message"),
    VIDEO(4, "视频消息", "Video Message"),
    FILE(5, "文件消息", "File Message"),
    LOCATION(6, "位置消息", "Location Message"),
    CONTACT(7, "名片消息", "Contact Message"),


    // === 富媒体消息 ===
    RICH_TEXT(11, "富文本消息", "Rich Text Message"),
    MARKDOWN(12, "Markdown消息", "Markdown Message"),
    HTML(13, "HTML消息", "HTML Message"),

    // === 复合消息 ===
    QUOTE(21, "引用消息", "Quote Message"),
    FORWARD(22, "转发消息", "Forward Message"),
    MERGE(23, "合并消息", "Merged Message"),
    CARD(24, "卡片消息", "Card Message"),

    // === 互动消息 ===
    REACTION(31, "表情回应", "Reaction Message"),
    VOTE(32, "投票消息", "Vote Message"),
    RED_PACKET(33, "红包消息", "Red Packet Message"),
    GAME(34, "小游戏消息", "Mini Game Message"),

    // === 通知消息 ===
    GROUP_NOTIFY(41, "群通知", "Group Notification"),
    FRIEND_NOTIFY(42, "好友通知", "Friend Notification"),
    SYSTEM_NOTIFY(43, "系统通知", "System Notification"),
    CUSTOM_NOTIFY(44, "自定义通知", "Custom Notification"),
    GROUP_CREATED(44, "群聊创建成功", "GROUP_CREATED succeed"),
    MSG_READ(45, "消息已读", "MSG_READ"),
    MSG_RECALL(46, "撤回消息", "Message Recall"),

    // === 状态消息 ===
    TYPING(51, "正在输入", "Typing"),
    TYPING_END(52, "结束输入", "Typing End"),

    // === WebRTC信令类型 ===
    WEBRTC_SIGNAL(61, "音视频信令", "WebRTC Signal"),
    WEBRTC_STATUS(62, "通话状态", "Call Status"),
    WEBRTC_QUALITY(63, "质量数据", "Quality Data"),

    // === 屏幕共享类型 ===
    SCREEN_SHARE(71, "屏幕共享", "Screen Share"),
    SCREEN_CONTROL(72, "共享控制", "Share Control"),

    // === 自定义消息 ===
    CUSTOM(91, "自定义消息", "Custom Message"),
    TEMPLATE(92, "模板消息", "Template Message");

    private final int code;
    private final String descChinese;
    private final String descEnglish;

    MsgTypeEnum(int code, String descChinese, String descEnglish) {
        this.code = code;
        this.descChinese = descChinese;
        this.descEnglish = descEnglish;
    }

}
