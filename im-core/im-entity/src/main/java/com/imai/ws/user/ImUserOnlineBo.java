package com.imai.ws.user;

import lombok.Data;

/**
 * 用户在线状态业务对象
 *
 * @author wei
 * @date 2025-01-17
 */
@Data
public class ImUserOnlineBo  {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 最后心跳时间
     */
    private Long lastHeartbeatTime;

    /**
     * 是否在线
     */
    private Boolean online;

    /**
     * 扩展信息
     */
    private String extras;
}
