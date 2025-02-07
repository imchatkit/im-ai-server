package com.imai.handler.sync;

import com.imai.ws.WebSocketMessage;

import java.util.List;

/**
 *  IM 消息同步器
 *  保存多端同步数据库
 *  下发在线消息
 */
public interface ImSyncHandler {

    /**
     *   消息同步器
     * @return
     */
    boolean sync(WebSocketMessage webSocketMessage, List<Long> receiverIds);
}
