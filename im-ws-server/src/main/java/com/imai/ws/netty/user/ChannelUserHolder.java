package com.imai.ws.netty.user;


import com.imai.ws.netty.config.ChannelAttributes;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Netty的channel与用户id关系的键值对，支持一个用户多个连接，并保存设备类型信息
 * <p>
 * 该类用于管理用户ID与连接ID之间的关系，以及每个连接的设备类型信息
 *
 * @author wei
 * @date 2024/10/27 11:32
 */
@Component
@Slf4j
public class ChannelUserHolder {

    // 用户ID与连接ID集合的映射关系, Key: userId, Value: channelId集合
    private final Map<Long, Set<String>> userChannelsMap = new ConcurrentHashMap<>();

    // 连接ID与Channel对象的映射关系, Key: channelId, Value: Netty.Channel
    private final Map<String, Channel> channelMap = new ConcurrentHashMap<>();

    /**
     * 添加新的Channel到管理器中
     *
     * @param userId     用户ID
     * @param channel    Channel对象
     * @param deviceType 设备类型，如 PC、手机等
     */
    public void addChannel(Long userId, Channel channel, String deviceType) {
        if (userId == null || channel == null) {
//            log.warn("Invalid input: userId or channel is null");
            return;
        }

        String channelId = channel.id().asLongText();
        if (channelId == null) {
            log.warn("Invalid channel ID for user: {}", userId);
            return;
        }

        // 检查是否已有相同ID的连接存在
        if (channelMap.containsKey(channelId)) {
            log.warn("Channel with ID {} already exists, replacing old connection", channelId);
            removeChannel(channelMap.get(channelId)); // 先移除旧的连接
        }

        // 添加连接ID到用户ID的映射关系
        userChannelsMap.computeIfAbsent(userId, k -> new ConcurrentSkipListSet<>()).add(channelId);

        // 将连接ID与Channel对象关联存储
        channelMap.put(channelId, channel);

        channel.attr(ChannelAttributes.USER_ID).set(userId);
        channel.attr(ChannelAttributes.DEVICE_TYPE).set(deviceType);

        log.info("Added_channel:{},user:{}", channelId, userId);
    }

    /**
     * 根据用户ID获取其所有连接ID集合
     *
     * @param userId 用户ID
     * @return 该用户的所有连接ID集合
     */
    public Set<String> getChannelsByUserId(Long userId) {
        if (userId == null) {
            log.warn("Invalid input: userId is null");
            return Collections.emptySet();
        }

        Set<String> channelIds = userChannelsMap.get(userId);
        if (channelIds == null) {
            return Collections.emptySet();
        }

        // 返回不可变集合视图，防止外部修改
        return Collections.unmodifiableSet(channelIds);
    }

    public Channel getChannelByChannelId(String channelId) {
        if (channelId == null) {
            log.warn("Invalid input: channelId is null");
            return null;
        } else {
            return channelMap.get(channelId);
        }
    }

    /**
     * 移除指定的连接ID及其关联信息
     *
     * @param channel Channel对象
     */
    public void removeChannel(Channel channel) {
        if (channel == null) {
            log.warn("Invalid input: channel is null");
            return;
        }

        String channelId = channel.id().asLongText();
        if (channelId == null) {
            log.warn("Invalid channel ID for channel: {}", channel);
            return;
        }

        // 从channel的attribute中获取用户id
        Long userId = channel.attr(ChannelAttributes.USER_ID).get();
        if (userId == null) {
            log.warn("Invalid user ID for channel: {}", channelId);
            return;
        }

        // 使用ConcurrentMap的原子操作移除Channel对象
        Channel removedChannel = channelMap.remove(channelId);
        if (removedChannel == null) {
            log.warn("Channel with ID {} not found in channelMap", channelId);
            return;
        }

        // 从用户ID映射中获取连接ID集合
        Set<String> channelIds = userChannelsMap.get(userId);
        if (channelIds != null) {
            // 使用ConcurrentSkipListSet的原子操作删除channelId
            boolean removed = channelIds.remove(channelId);
            if (removed) {
                log.info("Removed channel {} for user {}", channelId, userId);
            } else {
                log.warn("Failed to remove channel {} from user {}'s channel list", channelId, userId);
            }

            // 如果用户没有其他连接，移除用户ID的映射
            if (channelIds.isEmpty()) {
                userChannelsMap.remove(userId);
            }
        } else {
            log.warn("User {} does not have any channels in userChannelsMap", userId);
        }
    }
}
