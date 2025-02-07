package com.imai.ws.utils;

import com.imai.ws.WebSocketMessage;
import com.imai.ws.enums.ImResponseCode;
import com.imai.ws.enums.MessageDirection;

/**
 * WebSocket响应工具类
 */
public class WebSocketResponseUtil {

    public static WebSocketMessage error(Integer cmd, String requestId, ImResponseCode responseCode) {
        return WebSocketMessage.builder()
            .direction(MessageDirection.RESPONSE.getCode())
            .cmd(cmd)
            .code(responseCode.getCode())
            .message(responseCode.getDescChinese())
            .build();
    }

    public static WebSocketMessage success(Integer cmd, String requestId) {
        return WebSocketMessage.builder()
            .direction(MessageDirection.RESPONSE.getCode())
            .cmd(cmd)
            .code(ImResponseCode.SUCCESS.getCode())
            .message(ImResponseCode.SUCCESS.getDescChinese())
            .build();
    }

    public static WebSocketMessage success(Integer cmd, String requestId, Object data) {
        return WebSocketMessage.builder()
            .direction(MessageDirection.RESPONSE.getCode())
            .cmd(cmd)
            .code(ImResponseCode.SUCCESS.getCode())
            .message(ImResponseCode.SUCCESS.getDescChinese())
            .data(data)
            .build();
    }
}
