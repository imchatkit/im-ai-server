package com.imai.handler.filter;

/**
 * IM 消息过滤器
 * 1.判断消息敏感词
 * 2.判断是否在会话中
 * 3.判断是否在黑名单中
 * 4.判断是合法用户
 */
public interface ImMsgFilterHandler {

    /**
     * 消息过滤器
     *
     * @param message 消息内容
     * @param userId 用户ID
     * @param channelId 通道ID
     * @return 过滤结果
     */
    FilterResult filter(String message, Long userId, String channelId);
}
