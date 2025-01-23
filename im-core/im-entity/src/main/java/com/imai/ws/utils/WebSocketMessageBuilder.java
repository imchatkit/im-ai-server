package com.imai.ws.utils;

import com.imai.ws.WebSocketMessage;
import com.imai.ws.Header;
import com.imai.ws.Route;
import com.imai.ws.Content;
import com.imai.ws.Control;
import com.imai.ws.Options;
import com.imai.ws.constants.WebSocketConstants;
import com.imai.ws.enums.WebSocketEnums;

/**
 * WebSocket消息构建工具类
 */
public class WebSocketMessageBuilder {

    /**
     * 构建基础消息
     */
    public static WebSocketMessage buildBaseMessage() {
        WebSocketMessage message = new WebSocketMessage();
        
        // 设置消息头
        Header header = new Header();
        header.setVer(WebSocketConstants.PROTOCOL_VERSION);
        header.setTimestamp(System.currentTimeMillis());
        message.setHeader(header);
        
        return message;
    }

    /**
     * 构建文本消息
     */
    public static WebSocketMessage buildTextMessage(String text, String from, String to) {
        WebSocketMessage message = buildBaseMessage();
        
        // 设置路由信息
        Route route = new Route();
        route.setCmd(WebSocketEnums.CmdType.SINGLE_CHAT.getCode());
        route.setType(WebSocketEnums.MsgType.TEXT.getCode());
        route.setFrom(from);
        route.setTo(to);
        route.setRoomType(WebSocketEnums.RoomType.SINGLE.getCode());
        route.setSource(WebSocketConstants.Source.WEBSOCKET);
        message.setRoute(route);
        
        // 设置消息内容
        Content content = new Content();
        content.setText(text);
        message.setContent(content);
        
        // 设置控制信息
        Control control = new Control();
        control.setPersistent(true);
        control.setSyncDevice(true);
        control.setPriority(WebSocketConstants.Priority.NORMAL);
        message.setControl(control);
        
        // 设置可选配置
        Options options = new Options();
        message.setOptions(options);
        
        return message;
    }

    /**
     * 构建系统通知消息
     */
    public static WebSocketMessage buildSystemNotify(String text, String to) {
        WebSocketMessage message = buildBaseMessage();
        
        // 设置路由信息
        Route route = new Route();
        route.setCmd(WebSocketEnums.CmdType.SYS_NOTIFY.getCode());
        route.setType(WebSocketEnums.MsgType.SYSTEM_NOTIFY.getCode());
        route.setTo(to);
        route.setSource(WebSocketConstants.Source.SYSTEM);
        message.setRoute(route);
        
        // 设置消息内容
        Content content = new Content();
        content.setText(text);
        message.setContent(content);
        
        // 设置控制信息
        Control control = new Control();
        control.setPersistent(true);
        control.setSyncDevice(true);
        control.setPriority(WebSocketConstants.Priority.HIGH);
        message.setControl(control);
        
        return message;
    }

    /**
     * 构建错误消息
     */
    public static WebSocketMessage buildErrorMessage(int errorCode, String errorMsg) {
        WebSocketMessage message = buildBaseMessage();
        
        // 设置路由信息
        Route route = new Route();
        route.setCmd(WebSocketEnums.CmdType.ERROR.getCode());
        route.setType(WebSocketEnums.MsgType.SYSTEM_NOTIFY.getCode());
        route.setSource(WebSocketConstants.Source.SYSTEM);
        message.setRoute(route);
        
        // 设置消息内容
        Content content = new Content();
        content.setText(errorMsg);
        message.setContent(content);
        
        return message;
    }
} 