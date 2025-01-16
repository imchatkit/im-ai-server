package com.imai.handler.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author wei
 * @date 2025/1/16 10:45
 */
@DubboService
@Slf4j
public class ImMsgFilterHandlerImpl implements ImMsgFilterHandler {
    @Override
    public boolean filter(String message) {
        return false;
    }
}
