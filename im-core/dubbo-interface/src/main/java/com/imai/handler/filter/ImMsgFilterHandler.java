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
     * @param message
     * @return
     */
    boolean filter(String message, String userId, String channelId);
}
