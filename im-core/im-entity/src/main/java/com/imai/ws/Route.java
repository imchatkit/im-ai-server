package com.imai.ws;

import lombok.Data;
import java.util.List;

/**
 * 路由信息
 */
@Data
public class Route {
    private Integer cmd;            // 命令类型
    private Integer type;           // 消息类型
    private String room;            // 会话ID
    private Integer roomType;       // 会话类型(1单聊/2群聊/3聊天室等)
    private String from;            // 发送者ID
    private String to;              // 接收者ID
    private List<String> target;    // 目标用户列表
    private String source;          // 消息来源(serverApi/websocket)
} 