package com.imai.ws;

import java.io.Serializable;

import lombok.Data;
import lombok.Builder;
import lombok.ToString;

/**
 * 统一的WebSocket消息结构
 */
@Data
@Builder
@ToString
public class WebSocketMessage implements Serializable {
    // === 基础信息(所有消息都需要) ===
    private Integer direction;      // 消息流向(1:请求/2:响应/3:推送) MessageDirection
    private Integer cmd;            // 命令类型 CmdType
    private Header header;          // 消息头

    // === 请求消息字段 ===
    private Route route;            // 路由信息(direction=REQUEST时必填)
    private Content content;        // 消息内容(direction=REQUEST时必填)
    private Control control;        // 控制信息(direction=REQUEST时选填)

    // === 响应消息字段 ===
    private Integer code;           // 响应状态码(direction=RESPONSE时必填)
    private String message;         // 响应描述(direction=RESPONSE时必填)
    private Object data;            // 响应数据(direction=RESPONSE时选填)
    private Long timestamp;         // 服务器时间戳

    // === 推送消息字段 ===
    private MessageExtra messageExtra;     // 消息扩展信息(direction=PUSH时选填)


    public void settingDirection(Integer direction, Integer code, String message, Object data){
        this.direction = direction;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
