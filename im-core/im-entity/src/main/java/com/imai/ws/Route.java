package com.imai.ws;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 路由信息
 */
@Data
public class Route implements Serializable {

    private Integer type;           // 消息类型 MsgType
    private Long conversationId;   // 会话ID
//    private Integer conversationType;       // 会话类型(1单聊/2群聊/3聊天室等) ConversationType
//    private Long from;            // 发送者ID(可为空)
//    private Long to;              // 接收者ID,群聊可为空
    private List<Long> target;    // 目标用户列表,群聊仅某些用户查看消息
    private String source;          // 消息来源(serverApi/websocket)
}
