package com.imai.handler.sync;

import com.imai.ws.WebSocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * @author wei
 * @date 2025/1/16 10:45
 */
@DubboService
@Slf4j
public class ImSyncImpl implements ImSync {
    @Override
    public boolean sync(WebSocketMessage webSocketMessage, List<Long> receiverIds) {
        return false;
    }
}
