package com.imai.ws;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import lombok.Builder;
/**
 * im响应消息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImResponse implements Serializable {
    private Integer code;           // 响应状态码(direction=RESPONSE时必填)
    private String message;         // 响应描述(direction=RESPONSE时必填)
    private Object data;            // 响应数据(direction=RESPONSE时选填)
}
