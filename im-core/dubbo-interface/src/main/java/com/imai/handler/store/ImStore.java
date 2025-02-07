package com.imai.handler.store;

import com.imai.core.domain.bo.ImMessageBo;
import com.imai.ws.WebSocketMessage;

import java.util.List;

/**
 *  IM 消息分发器
 *  1.存消息数据库
 *  2.存已读未读数据库
 */
public interface ImStore {

    /**
     *  分发消息
     * @return
     */
    boolean store(ImMessageBo bo, WebSocketMessage webSocketMessage, List<Long> receiverIds);
}
