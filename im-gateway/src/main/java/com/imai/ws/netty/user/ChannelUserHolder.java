package com.imai.ws.netty.user;

import com.imai.ws.netty.config.ImChannelAttributes;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

/**
 * Netty Channel管理器
 * 管理WebSocket连接的Channel与用户关系，支持用户多端在线
 *
 * @author wei
 */
@Component
@Slf4j
public class ChannelUserHolder {

    /**
     * 用户ID -> 该用户的所有channelId集合
     * Key: userId
     * Value: Set<channelId>
     */
    private final Map<Long, Set<String>> userChannelsMap = new ConcurrentHashMap<>();

    /**
     * channelId -> Channel对象映射
     * Key: channelId
     * Value: Channel
     */
    private final Map<String, Channel> channelMap = new ConcurrentHashMap<>();

    /**
     * 添加新的Channel连接
     *
     * @param userId     用户ID
     * @param channel    Channel对象
     * @param deviceType 设备类型
     * @return 是否添加成功
     */
    public boolean addChannel(Long userId, Channel channel, String deviceType) {
        try {
            // 参数校验
            Assert.notNull(userId, "UserId cannot be null");
            Assert.notNull(channel, "Channel cannot be null");
            Assert.hasText(deviceType, "DeviceType cannot be empty");

            String channelId = channel.id().asLongText();
            Assert.hasText(channelId, "ChannelId cannot be empty");

            // 如果已存在相同channelId的连接，先移除旧连接
            Channel existingChannel = channelMap.get(channelId);
            if (existingChannel != null) {
                log.info("[channel_replace] Replacing existing channel - userId:{}, channelId:{}, deviceType:{}",
                    userId, channelId, deviceType);
                removeChannel(existingChannel);
            }

            // 添加用户与channelId的映射
            Set<String> userChannels = userChannelsMap.computeIfAbsent(userId,
                k -> new ConcurrentSkipListSet<>());
            userChannels.add(channelId);

            // 保存channelId与Channel的映射
            channelMap.put(channelId, channel);

            // 设置Channel属性
            channel.attr(ImChannelAttributes.USER_ID).set(userId);
            channel.attr(ImChannelAttributes.DEVICE_TYPE).set(deviceType);

            log.info("[channel_add] Channel added successfully - userId:{}, channelId:{}, deviceType:{}",
                userId, channelId, deviceType);
            return true;
        } catch (Exception e) {
            log.error("[channel_add_error] Failed to add channel - userId:{}, error:{}", 
                userId, e.getMessage(), e);
            return false;
        }
    }

    /**
     * 获取用户的所有在线Channel
     *
     * @param userId 用户ID
     * @return 用户的所有在线Channel列表
     */
    public List<Channel> getUserChannels(Long userId) {
        Assert.notNull(userId, "UserId cannot be null");

        Set<String> channelIds = userChannelsMap.getOrDefault(userId, Collections.emptySet());
        return channelIds.stream()
            .map(this::getChannelByChannelId)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    /**
     * 获取用户指定设备类型的Channel
     *
     * @param userId     用户ID
     * @param deviceType 设备类型
     * @return 指定设备类型的Channel列表
     */
    public List<Channel> getUserChannelsByDevice(Long userId, String deviceType) {
        Assert.notNull(userId, "UserId cannot be null");
        Assert.hasText(deviceType, "DeviceType cannot be empty");

        return getUserChannels(userId).stream()
            .filter(channel -> deviceType.equals(channel.attr(ImChannelAttributes.DEVICE_TYPE).get()))
            .collect(Collectors.toList());
    }

    /**
     * 获取用户的所有channelId
     *
     * @param userId 用户ID
     * @return channelId集合
     */
    public Set<String> getChannelsByUserId(Long userId) {
        Assert.notNull(userId, "UserId cannot be null");
        return Collections.unmodifiableSet(
            userChannelsMap.getOrDefault(userId, Collections.emptySet())
        );
    }

    /**
     * 根据channelId获取Channel对象
     *
     * @param channelId channelId
     * @return Channel对象
     */
    public Channel getChannelByChannelId(String channelId) {
        Assert.hasText(channelId, "ChannelId cannot be empty");
        return channelMap.get(channelId);
    }

    /**
     * 移除Channel连接
     *
     * @param channel Channel对象
     * @return 是否移除成功
     */
    public boolean removeChannel(Channel channel) {
        try {
            Assert.notNull(channel, "Channel cannot be null");
            String channelId = channel.id().asLongText();
            Assert.hasText(channelId, "ChannelId cannot be empty");

            // 获取用户ID
            Long userId = channel.attr(ImChannelAttributes.USER_ID).get();
            if (userId == null) {
                log.warn("[channel_remove_warn] UserId not found for channel:{}", channelId);
                return false;
            }

            // 移除Channel映射
            Channel removedChannel = channelMap.remove(channelId);
            if (removedChannel == null) {
                log.warn("[channel_remove_warn] Channel not found in channelMap:{}", channelId);
                return false;
            }

            // 移除用户Channel映射
            Set<String> userChannels = userChannelsMap.get(userId);
            if (userChannels != null) {
                userChannels.remove(channelId);
                // 如果用户没有其他Channel，则移除用户映射
                if (userChannels.isEmpty()) {
                    userChannelsMap.remove(userId);
                }
                log.info("[channel_remove] Channel removed successfully - userId:{}, channelId:{}", 
                    userId, channelId);
                return true;
            }

            log.warn("[channel_remove_warn] User channels not found - userId:{}, channelId:{}", 
                userId, channelId);
            return false;
        } catch (Exception e) {
            log.error("[channel_remove_error] Failed to remove channel - error:{}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 获取当前在线用户数
     *
     * @return 在线用户数
     */
    public int getOnlineUserCount() {
        return userChannelsMap.size();
    }

    /**
     * 获取当前总连接数
     *
     * @return 总连接数
     */
    public int getTotalConnectionCount() {
        return channelMap.size();
    }

    /**
     * 判断用户是否在线
     *
     * @param userId 用户ID
     * @return 是否在线
     */
    public boolean isUserOnline(Long userId) {
        Assert.notNull(userId, "UserId cannot be null");
        Set<String> channels = userChannelsMap.get(userId);
        return channels != null && !channels.isEmpty();
    }

    /**
     * 获取用户在指定设备类型上的在线状态
     *
     * @param userId     用户ID
     * @param deviceType 设备类型
     * @return 是否在该设备类型上在线
     */
    public boolean isUserOnlineOnDevice(Long userId, String deviceType) {
        Assert.notNull(userId, "UserId cannot be null");
        Assert.hasText(deviceType, "DeviceType cannot be empty");
        
        return getUserChannelsByDevice(userId, deviceType).stream()
            .anyMatch(Channel::isActive);
    }

    /**
     * 根据Channel获取用户ID
     */
    public Long getUserIdByChannel(Channel channel) {
        return channel.attr(ImChannelAttributes.USER_ID).get();
    }
}