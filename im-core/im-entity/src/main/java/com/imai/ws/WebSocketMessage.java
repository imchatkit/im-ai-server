package com.imai.ws;

import java.io.Serializable;

import lombok.Data;

/**
 * WebSocket消息基础结构
 */
@Data
public class WebSocketMessage implements Serializable {
    private Header header;      // 消息头
    private Route route;        // 路由信息
    private Content content;    // 消息内容
    private Control control;    // 控制信息
    private Options options;    // 可选配置
}