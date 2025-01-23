package com.imai.ws.enums;

import lombok.Getter;

/**
 * WebSocket消息枚举定义
 */
public class WebSocketEnums {

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

    /**
     * 会话类型枚举
     */
    @Getter
    public enum RoomType {
        SINGLE(1, "单聊"),
        GROUP(2, "群聊"),
        CHATROOM(3, "聊天室");

        private final int code;
        private final String desc;

        RoomType(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }

    /**
     * 回执类型枚举
     */
    @Getter
    public enum ReceiptType {
        DELIVERED(1, "送达回执"),
        READ(2, "已读回执");

        private final int code;
        private final String desc;

        ReceiptType(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }
} 