package com.imai.ai.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface TipsService {

    void getTips(Long conversationId, SseEmitter emitter,  Long userId );
}
