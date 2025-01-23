package com.imai.ws.enums;

import lombok.Getter;

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