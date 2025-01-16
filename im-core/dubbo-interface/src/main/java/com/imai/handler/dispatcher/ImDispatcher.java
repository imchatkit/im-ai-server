package com.imai.handler.dispatcher;

/**
 *  IM 消息分发器
 *  1.存消息数据库
 *  2.存已读未读数据库
 */
public interface ImDispatcher {

    /**
     *  分发消息
     * @param message
     * @return
     */
    boolean dispatch(String message);
}
