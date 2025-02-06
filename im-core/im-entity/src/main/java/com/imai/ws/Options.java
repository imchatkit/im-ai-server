package com.imai.ws;

import java.io.Serializable;

import lombok.Data;

/**
 * 可选配置

 */
@Data
public class Options implements Serializable{

    private Conversation conversation;  // 会话配置

    private String extra;   // 额外扩展字段
} 