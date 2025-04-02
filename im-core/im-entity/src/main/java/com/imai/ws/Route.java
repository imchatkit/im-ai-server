package com.imai.ws;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import lombok.Builder;
/**
 * 路由信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route implements Serializable {

    private Integer type;           // 消息类型 MsgType
    private Long conversationId;   // 会话ID
//    private Long from;            // 发送者ID(可为空)
//    private Long to;              // 接收者ID,群聊可为空
    private List<Long> target;    // 目标用户列表,群聊仅某些用户查看消息
    private String source;          // 消息来源(server/serverApi/websocket)
}
