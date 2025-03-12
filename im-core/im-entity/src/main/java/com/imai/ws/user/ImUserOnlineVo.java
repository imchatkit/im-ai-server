package com.imai.ws.user;

import lombok.Data;

import java.util.List;

/**
 * 用户在线状态视图对象
 *
 * @author wei
 * @date 2025-01-17
 */
@Data
public class ImUserOnlineVo {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 是否在线
     */
    private Boolean online;

    /**
     * 在线设备列表
     */
    private List<String> devices;

    /**
     * 最后心跳时间
     */
    private Long lastHeartbeatTime;

    /**
     * 扩展信息
     */
    private String extras;
}
