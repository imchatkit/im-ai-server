package com.imai.ws;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 消息头
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Header implements Serializable {
    private String localId;         // 客户端序列号
    //    private String platform;        // 平台类型
//    private String deviceId;        // 设备ID
    private Long timestamp;         // 客户端发送时间
}
