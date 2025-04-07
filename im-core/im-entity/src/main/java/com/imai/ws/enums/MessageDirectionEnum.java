package com.imai.ws.enums;

import java.io.Serializable;

import lombok.Getter;

/**
 * 消息流向
 */
@Getter
public enum MessageDirectionEnum implements Serializable{
    REQUEST(1, "客户端请求", "Client Request"),
    RESPONSE(2, "服务端响应", "Server Response"),
    PUSH(3, "服务端推送", "Server Push");

    private final int code;
    private final String desc;
    private final String descEnglish;

    MessageDirectionEnum(int code, String desc, String descEnglish  ) {
        this.code = code;
        this.desc = desc;
        this.descEnglish = descEnglish;
    }
}
