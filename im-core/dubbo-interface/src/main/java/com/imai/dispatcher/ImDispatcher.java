package com.imai.dispatcher;

/**
 *  IM 消息分发器
 */
public interface ImDispatcher {

    /**
     *  分发消息
     * @param message
     * @return
     */
    boolean dispatch(String message);
}
