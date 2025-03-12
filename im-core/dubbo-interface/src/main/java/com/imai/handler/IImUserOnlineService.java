package com.imai.handler;


import com.imai.ws.user.ImUserOnlineBo;
import com.imai.ws.user.ImUserOnlineVo;

import java.util.Collection;
import java.util.List;

/**
 * 用户在线状态Service接口
 *
 * @author wei
 * @date 2025-01-17
 */
public interface IImUserOnlineService {

    /**
     * 查询用户在线状态
     *
     * @param userId 用户ID
     * @return 用户在线状态
     */
    ImUserOnlineVo queryByUserId(Long userId);

    /**
     * 查询用户在线状态列表
     *
     * @param userIds 用户ID列表
     * @return 用户在线状态列表
     */
    List<ImUserOnlineVo> queryByUserIds(Collection<Long> userIds);

    /**
     * 更新用户在线状态
     *
     * @param bo 用户在线状态
     * @return 是否更新成功
     */
    Boolean updateOnlineStatus(ImUserOnlineBo bo);

    /**
     * 用户上线
     *
     * @param userId     用户ID
     * @param deviceType 设备类型
     * @param clientId   客户端ID
     * @return 是否上线成功
     */
    Boolean online(Long userId, String deviceType, String clientId);

    /**
     * 用户下线
     *
     * @param userId     用户ID
     * @param deviceType 设备类型
     * @param clientId   客户端ID
     * @return 是否下线成功
     */
    Boolean offline(Long userId, String deviceType, String clientId);

    /**
     * 批量获取用户在线状态
     *
     * @param userIds 用户ID列表
     * @return 用户在线状态Map，key为用户ID，value为是否在线
     */
    java.util.Map<Long, Boolean> batchGetOnlineStatus(Collection<Long> userIds);

    /**
     * 获取用户在线的设备列表
     *
     * @param userId 用户ID
     * @return 设备类型列表
     */
    List<String> getOnlineDevices(Long userId);

    /**
     * 清理过期的在线状态记录
     *
     * @param timeoutSeconds 超时时间（秒）
     * @return 清理的记录数
     */
    int cleanExpiredOnlineStatus(int timeoutSeconds);
}
