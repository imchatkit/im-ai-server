package com.imai.ws.enums;

import lombok.Getter;

@Getter
public enum ImResponseCodeEnum {
    SUCCESS(200, "成功", "Success"),
    BAD_REQUEST(400, "请求错误", "Bad Request"),
    INTERNAL_ERROR(500, "服务器内部错误", "Internal Server Error"),

    // 会话相关错误 1000-1999
    RECEIVER_NOT_IN_CONVERSATION(1001, "接收方不在会话中", "Receiver Not In Conversation"),
    SENDER_NOT_IN_CONVERSATION(1002, "发送方不在会话中", "Sender Not In Conversation"),
    NOT_CONVERSATION(1003, "会话不存在", "Not Conversation"),
    MESSAGE_FORMAT_ERROR(1004, "消息格式错误", "Message Format Error"),
    MESSAGE_INCOMPLETE(1005, "消息格式不完整", "Message Incomplete"),

    // 群组相关错误 2000-2999
    GROUP_DISSOLVED(2001, "群组已解散", "Group Dissolved"),
    USER_MUTED(2002, "您已被禁言", "User Muted"),
    NOT_GROUP_MEMBER(2003, "您不是群成员", "Not Group Member"),
    GROUP_FULL(2004, "群组已满", "Group Full"),

    // 消息处理错误 3000-3999
    MESSAGE_STORE_FAILED(3001, "消息保存失败", "Message Store Failed"),
    MESSAGE_PROCESS_FAILED(3002, "消息处理失败", "Message Process Failed"),
    UNSUPPORTED_MESSAGE_TYPE(3003, "不支持的消息类型", "Unsupported Message Type"),

    // 其他错误码...
    ;

    private final int code;
    private final String descChinese;
    private final String descEnglish;

    ImResponseCodeEnum(int code, String descChinese, String descEnglish) {
        this.code = code;
        this.descChinese = descChinese;
        this.descEnglish = descEnglish;
    }
}
