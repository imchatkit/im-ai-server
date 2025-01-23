package com.imai.ws.enums;

import lombok.Getter;

/**
 * 命令类型枚举
 */
@Getter
public enum CmdType {
    // === 消息命令 ===
    SINGLE_CHAT(1, "单聊消息"),
    GROUP_CHAT(2, "群聊消息"),
    CHATROOM_CHAT(3, "聊天室消息"),
    BROADCAST(4, "广播消息"),
    CUSTOM_CHAT(5, "自定义消息"),
    
    // === 消息操作 ===
    MSG_RECALL(11, "撤回消息"),
    MSG_DELETE(12, "删除消息"),
    MSG_EDIT(13, "编辑消息"),
    MSG_REACTION(14, "消息回应"),
    
    // === 已读命令 ===
    MSG_RECEIPT(21, "消息回执"),
    MSG_READ(22, "消息已读"),
    READ_ALL(23, "全部已读"),
    READ_CANCEL(24, "取消已读"),
    
    // === 会话命令 ===
    CONVERSATION_CREATE(31, "创建会话"),
    CONVERSATION_DELETE(32, "删除会话"),
    CONVERSATION_UPDATE(33, "更新会话"),
    CONVERSATION_SYNC(34, "同步会话"),
    
    // === 群组命令 ===
    GROUP_CREATE(41, "创建群组"),
    GROUP_JOIN(42, "加入群组"),
    GROUP_LEAVE(43, "退出群组"),
    GROUP_DISMISS(44, "解散群组"),
    GROUP_UPDATE(45, "更新群组"),
    GROUP_MUTE(46, "群组禁言"),
    
    // === 用户命令 ===
    USER_MUTE(51, "用户禁言"),
    USER_BLOCK(52, "用户拉黑"),
    USER_ONLINE(53, "用户上线"),
    USER_OFFLINE(54, "用户离线"),
    USER_SETTING(55, "用户设置"),
    
    // === 关系命令 ===
    FRIEND_ADD(61, "添加好友"),
    FRIEND_DELETE(62, "删除好友"),
    FRIEND_UPDATE(63, "更新好友"),
    FRIEND_BLOCK(64, "拉黑好友"),
    
    // === 同步命令 ===
    SYNC_MSG(71, "同步消息"),
    SYNC_CONVERSATION(72, "同步会话"),
    SYNC_GROUP(73, "同步群组"),
    SYNC_FRIEND(74, "同步好友"),
    
    // === 系统命令 ===
    SYS_NOTIFY(91, "系统通知"),
    CUSTOM_NOTIFY(92, "自定义通知"),
    ERROR(99, "错误消息"),
    
    // === 输入状态命令 ===
    TYPING_STATUS(81, "输入状态"),
    TYPING_CANCEL(82, "取消输入"),
    
    // === WebRTC信令命令 ===
    WEBRTC_INVITE(101, "音视频邀请"),
    WEBRTC_ANSWER(102, "音视频应答"),
    WEBRTC_CANDIDATE(103, "候选者更新"),
    WEBRTC_STATUS(104, "通话状态"),
    WEBRTC_SWITCH(105, "切换音视频"),
    WEBRTC_QUALITY(106, "质量报告"),
    
    // === 屏幕共享命令 ===
    SCREEN_SHARE_START(111, "开始共享"),
    SCREEN_SHARE_STOP(112, "停止共享"),
    SCREEN_SHARE_QUALITY(113, "共享质量");

    private final int code;
    private final String desc;

    CmdType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
} 