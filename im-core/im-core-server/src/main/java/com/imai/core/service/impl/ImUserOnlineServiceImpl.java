package com.imai.core.service.impl;

import com.imai.ws.user.ImUserOnlineBo;
import com.imai.ws.user.ImUserOnlineVo;
import com.imai.handler.IImUserOnlineService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.common.redis.utils.RedisUtils;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

/**
 * 用户在线状态Service实现类
 * 使用Redis实现用户在线状态管理，支持多端同步
 *
 * @author wei
 * @date 2025-01-17
 */
@Slf4j
@Service
@DubboService
public class ImUserOnlineServiceImpl implements IImUserOnlineService {

    private static final String ONLINE_USER_KEY = "im:online:user:";
    private static final String ONLINE_DEVICE_KEY = "im:online:device:";
    private static final int DEFAULT_EXPIRE_TIME = 300; // 5分钟过期

    @Override
    public ImUserOnlineVo queryByUserId(Long userId) {
        String userKey = ONLINE_USER_KEY + userId;
        Boolean isOnline = RedisUtils.hasKey(userKey);
        ImUserOnlineVo vo = new ImUserOnlineVo();
        vo.setUserId(userId);
        vo.setOnline(isOnline != null && isOnline);
        vo.setDevices(getOnlineDevices(userId));
        return vo;
    }

    @Override
    public List<ImUserOnlineVo> queryByUserIds(Collection<Long> userIds) {
        List<ImUserOnlineVo> result = new ArrayList<>();
        for (Long userId : userIds) {
            result.add(queryByUserId(userId));
        }
        return result;
    }

    @Override
    public Boolean updateOnlineStatus(ImUserOnlineBo bo) {
        try {
            String userKey = ONLINE_USER_KEY + bo.getUserId();
            String deviceKey = ONLINE_DEVICE_KEY + bo.getUserId();

            RedisUtils.setCacheObject(userKey, true, Duration.ofSeconds(DEFAULT_EXPIRE_TIME));
            RedisUtils.setCacheMapValue(deviceKey, bo.getDeviceType(), bo.getClientId());
            RedisUtils.expire(deviceKey, Duration.ofSeconds(DEFAULT_EXPIRE_TIME));

            return true;
        } catch (Exception e) {
            log.error("更新用户在线状态失败", e);
            return false;
        }
    }

    @Override
    public Boolean online(Long userId, String deviceType, String clientId) {
        ImUserOnlineBo bo = new ImUserOnlineBo();
        bo.setUserId(userId);
        bo.setDeviceType(deviceType);
        bo.setClientId(clientId);
        return updateOnlineStatus(bo);
    }

    @Override
    public Boolean offline(Long userId, String deviceType, String clientId) {
        try {
            String deviceKey = ONLINE_DEVICE_KEY + userId;
            RedisUtils.delCacheMapValue(deviceKey, deviceType);

            // 如果没有任何设备在线，则删除用户在线状态
            if (RedisUtils.getCacheMapKeySet(deviceKey).isEmpty()) {
                String userKey = ONLINE_USER_KEY + userId;
                RedisUtils.deleteObject(userKey);
                RedisUtils.deleteObject(deviceKey);
            }

            return true;
        } catch (Exception e) {
            log.error("用户下线失败", e);
            return false;
        }
    }

    @Override
    public Map<Long, Boolean> batchGetOnlineStatus(Collection<Long> userIds) {
        Map<Long, Boolean> result = new HashMap<>();
        for (Long userId : userIds) {
            String userKey = ONLINE_USER_KEY + userId;
            Boolean isOnline = RedisUtils.hasKey(userKey);
            result.put(userId, isOnline != null && isOnline);
        }
        return result;
    }

    @Override
    public List<String> getOnlineDevices(Long userId) {
        String deviceKey = ONLINE_DEVICE_KEY + userId;
        Set<String> devices = RedisUtils.getCacheMapKeySet(deviceKey);
        return new ArrayList<>(devices);
    }

    @Override
    public int cleanExpiredOnlineStatus(int timeoutSeconds) {
        // TODO: 实现清理过期在线状态的逻辑
        return 0;
    }
}
