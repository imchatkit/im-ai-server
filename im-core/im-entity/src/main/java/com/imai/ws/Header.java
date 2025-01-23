package com.imai.ws;

import lombok.Data;



/**
 * 消息头
 */
@Data
public class Header {
    private Integer ver;            // 协议版本号
    private String seq;             // 客户端序列号
    private String appId;           // 应用ID
    private String tenantId;        // 租户ID
    private String platform;        // 平台类型
    private String deviceId;        // 设备ID
    private Long timestamp;         // 客户端发送时间
}
