package com.imai.ws.enums;

import lombok.Getter;

@Getter
public enum ImResponseCode {
    SUCCESS(200, "成功", "Success"),
    BAD_REQUEST(400, "请求错误", "Bad Request"),
    
    // 会话相关错误 1000-1999
    RECEIVER_NOT_IN_CONVERSATION(1001, "接收方不在会话中", "Receiver Not In Conversation"),
    SENDER_NOT_IN_CONVERSATION(1002, "发送方不在会话中", "Sender Not In Conversation"),
    
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