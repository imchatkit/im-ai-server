package com.imai.ws.utils;

import com.imai.ws.Content;
import com.imai.ws.Control;
import com.imai.ws.Header;
import com.imai.ws.WebSocketMessage;

/**
 * WebSocket消息构建工具类
 */
public class WebSocketMessageBuilder {

    /**
     * 构建基础消息
     */
    public static WebSocketMessage buildBaseMessage() {
        Header header = new Header();
        header.setTimestamp(System.currentTimeMillis());

        return WebSocketMessage.builder()
            .header(header)
            .build();
    }

    /**
     * 构建文本消息
     */
    public static WebSocketMessage buildTextMessage(String text, String from, String to) {
        WebSocketMessage message = buildBaseMessage();


        // 设置消息内容
        Content content = new Content();
        content.setText(text);
        message.setContent(content);


        return message;
    }

    /**
     * 构建系统通知消息
     */
    public static WebSocketMessage buildSystemNotify(String text, String to) {
        WebSocketMessage message = buildBaseMessage();


        // 设置消息内容
        Content content = new Content();
        content.setText(text);
        message.setContent(content);

        // 设置控制信息
        Control control = new Control();
        control.setPersistent(true);
        message.setControl(control);

        return message;
    }

    /**
     * 构建错误消息
     */
    public static WebSocketMessage buildErrorMessage(int errorCode, String errorMsg) {
        WebSocketMessage message = buildBaseMessage();


        // 设置消息内容
        Content content = new Content();
        content.setText(errorMsg);
        message.setContent(content);

        return message;
    }
}
