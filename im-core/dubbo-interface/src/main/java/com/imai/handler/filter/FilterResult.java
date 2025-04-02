package com.imai.handler.filter;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息过滤结果
 */
@Data
public class FilterResult implements Serializable {
    /**
     * 是否通过过滤
     */
    private boolean success;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     * 创建成功结果
     */
    public static FilterResult success() {
        FilterResult result = new FilterResult();
        result.setSuccess(true);
        return result;
    }

    /**
     * 创建失败结果
     */
    public static FilterResult error(String errorMessage, Integer errorCode) {
        FilterResult result = new FilterResult();
        result.setSuccess(false);
        result.setErrorMessage(errorMessage);
        result.setErrorCode(errorCode);
        return result;
    }
}
