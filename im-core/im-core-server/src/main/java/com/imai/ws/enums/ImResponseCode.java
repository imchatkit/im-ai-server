package com.imai.ws.enums;

import lombok.Getter;

@Getter
public enum ImResponseCode {
    SUCCESS(200, "成功", "Success"),
    BAD_REQUEST(400, "请求错误", "Bad Request"),

    // 会话相关错误 1000-1999
    RECEIVER_NOT_IN_CONVERSATION(1001, "接收方不在会话中", "Receiver Not In Conversation"),
    SENDER_NOT_IN_CONVERSATION(1002, "发送方不在会话中", "Sender Not In Conversation"),

    // 群组相关错误 2000-2999
    GROUP_DISSOLVED(2001, "群组已解散", "Group Dissolved"),
    USER_MUTED(2002, "您已被禁言", "User Muted"),
    NOT_GROUP_MEMBER(2003, "您不是群成员", "Not Group Member"),
    GROUP_FULL(2004, "群组已满", "Group Full"),

    // 其他错误码...
    ;

    private final int code;
    private final String descChinese;
    private final String descEnglish;

    ImResponseCode(int code, String descChinese, String descEnglish) {
        this.code = code;
        this.descChinese = descChinese;
        this.descEnglish = descEnglish;
    }
}
