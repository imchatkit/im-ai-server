package com.imai.ws;

import java.io.Serializable;

import lombok.Data;

/**
 * 消息扩展信息
 */
@Data
public class MessageExtra implements Serializable   {
    private Boolean mentioned;          // 是否被@提及
    private Boolean mentionedAll;       // 是否@所有人
    private String senderNickname;      // 发送者昵称
    private String senderAvatar;        // 发送者头像
    private Boolean silent;             // 是否静默消息
    private Long pts;          // 消息序列号(direction=PUSH时必填)
    private Long messageId;       // 消息ID(direction=PUSH时必填)
    private Long timestamp;         // 服务器时间戳(direction=PUSH时必填)
}
