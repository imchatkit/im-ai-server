package com.imai.ws.enums;

import lombok.Getter;

/**
 * 请求命令类型枚举
 */
@Getter
public enum RequestCmdType {
    // === 消息命令 ===
    SINGLE_CHAT(1, "好友单聊消息", "Friend Private Message"), // 必须要是好友
    GROUP_CHAT(2, "群聊消息", "Group Message"),
    CHATROOM_CHAT(3, "聊天室消息", "Chatroom Message"),
    BROADCAST(4, "广播消息", "Broadcast Message"),
    CUSTOM_CHAT(5, "自定义消息", "Custom Message"),
    STRANGER_CHAT(6, "陌生人单聊消息", "Stranger Private Message"), // 可以不是好友

    // === 消息操作 ===
    MSG_RECALL(11, "撤回消息", "Message Recall"),
    MSG_DELETE(12, "删除消息", "Message Delete"),
    MSG_EDIT(13, "编辑消息", "Message Edit"),
    MSG_REACTION(14, "消息回应", "Message Reaction"),

    // === 已读命令 ===
    MSG_RECEIPT(21, "消息回执", "Message Receipt"),
    MSG_READ(22, "消息已读", "Message Read"),
    READ_ALL(23, "全部已读", "Read All"),
    READ_CANCEL(24, "取消已读", "Cancel Read"),

    // === 会话命令 ===
    CONVERSATION_CREATE(31, "创建会话", "Create Conversation"),
    CONVERSATION_DELETE(32, "删除会话", "Delete Conversation"),
    CONVERSATION_UPDATE(33, "更新会话", "Update Conversation"),
    CONVERSATION_SYNC(34, "同步会话", "Sync Conversation"),

    // === 群组命令 ===
    GROUP_CREATE(41, "创建群组", "Create Group"),
    GROUP_JOIN(42, "加入群组", "Join Group"),
    GROUP_LEAVE(43, "退出群组", "Leave Group"),
    GROUP_DISMISS(44, "解散群组", "Dismiss Group"),
    GROUP_UPDATE(45, "更新群组", "Update Group"),
    GROUP_MUTE(46, "群组禁言", "Group Mute"),

    // === 用户命令 ===
    USER_MUTE(51, "用户禁言", "User Mute"),
    USER_BLOCK(52, "用户拉黑", "User Block"),
    USER_ONLINE(53, "用户上线", "User Online"),
    USER_OFFLINE(54, "用户离线", "User Offline"),
    USER_SETTING(55, "用户设置", "User Settings"),

    // === 关系命令 ===
    FRIEND_ADD(61, "添加好友", "Add Friend"),
    FRIEND_DELETE(62, "删除好友", "Delete Friend"),
    FRIEND_UPDATE(63, "更新好友", "Update Friend"),
    FRIEND_BLOCK(64, "拉黑好友", "Block Friend"),

    // === 同步命令 ===
    SYNC_MSG(71, "同步消息", "Sync Messages"),
    SYNC_CONVERSATION(72, "同步会话", "Sync Conversations"),
    SYNC_GROUP(73, "同步群组", "Sync Groups"),
    SYNC_FRIEND(74, "同步好友", "Sync Friends"),

    // === 系统命令 ===
    SYS_NOTIFY(91, "系统通知", "System Notification"),
    CUSTOM_NOTIFY(92, "自定义通知", "Custom Notification"),
    ERROR(99, "错误消息", "Error Message"),
    NOTIFY(93, "通用通知", "Message"),

    // === 输入状态命令 ===
    TYPING_STATUS(81, "输入状态", "Typing Status"),
    TYPING_CANCEL(82, "取消输入", "Cancel Typing"),

    // === WebRTC信令命令 ===
    WEBRTC_INVITE(101, "音视频邀请", "WebRTC Invite"),
    WEBRTC_ANSWER(102, "音视频应答", "WebRTC Answer"),
    WEBRTC_CANDIDATE(103, "候选者更新", "WebRTC Candidate"),
    WEBRTC_STATUS(104, "通话状态", "Call Status"),
    WEBRTC_SWITCH(105, "切换音视频", "Switch Audio/Video"),
    WEBRTC_QUALITY(106, "质量报告", "Quality Report"),

    // === 屏幕共享命令 ===
    SCREEN_SHARE_START(111, "开始共享", "Start Screen Share"),
    SCREEN_SHARE_STOP(112, "停止共享", "Stop Screen Share"),
    SCREEN_SHARE_QUALITY(113, "共享质量", "Share Quality");

    private final int code;
    private final String descChinese;
    private final String descEnglish;

    RequestCmdType(int code, String descChinese, String descEnglish) {
        this.code = code;
        this.descChinese = descChinese;
        this.descEnglish = descEnglish;
    }
}
