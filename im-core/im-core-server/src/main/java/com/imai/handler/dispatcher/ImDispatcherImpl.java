package com.imai.handler.dispatcher;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author wei
 * @date 2025/1/10 10:31
 */
@DubboService
@Slf4j
public class ImDispatcherImpl implements ImDispatcher {

    /**
     *  
     */
    @Override
    public boolean dispatch(String message) {
        
        log.info("dispatch message:{}", message);
        return true;
    }
}
