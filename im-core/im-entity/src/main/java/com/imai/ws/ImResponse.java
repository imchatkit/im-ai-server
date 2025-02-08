package com.imai.ws;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * im响应消息
 */
@Data
public class ImResponse implements Serializable {
    private Integer code;           // 响应状态码(direction=RESPONSE时必填)
    private String message;         // 响应描述(direction=RESPONSE时必填)
    private Object data;            // 响应数据(direction=RESPONSE时选填)
}
