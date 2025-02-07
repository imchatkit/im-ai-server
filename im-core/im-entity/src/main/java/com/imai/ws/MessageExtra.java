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
//    private Integer messageType;        // 消息类型
//    private Long conversationId;        // 会话ID
//    private String conversationName;    // 会话名称
//    private String conversationAvatar;  // 会话头像
    private Boolean silent;             // 是否静默消息
    private Long pts;          // 消息序列号(direction=PUSH时必填)
    private Long messageId;       // 消息ID(direction=PUSH时必填)

}
