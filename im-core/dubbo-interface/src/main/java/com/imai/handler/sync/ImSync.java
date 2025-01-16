package com.imai.handler.sync;

/**
 *  IM 消息同步器
 *  保存多端同步数据库
 *  下发在线消息
 */
public interface ImSync {

    /**
     *   消息同步器
     * @param message
     * @return
     */
    boolean sync(String message);
}
