package com.imai.ai.sse;

import com.imai.ai.service.TipsService;

import lombok.extern.slf4j.Slf4j;

import org.dromara.common.satoken.utils.LoginHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * AI/提示
 * @author wei
 * @date 2025/3/3 15:43
 */
@RequestMapping("/api/v1/ai/")
@RestController
@Slf4j
public class TipsSseController {

    @Autowired
    private TipsService tipsService;

    /**
     * 获取提示
     * @param conversationId
     * @return
     */
    @GetMapping("/tips")
    public SseEmitter getTips(Long conversationId) {
        Long userId = LoginHelper.getUserId();
        // 设置超时时间为5分钟
        SseEmitter sseEmitter = new SseEmitter(300000L);

        // 设置完成回调
        sseEmitter.onCompletion(() -> {
            log.info("SSE completed for user: {}", userId);
        });

        // 设置超时回调
        sseEmitter.onTimeout(() -> {
            log.info("SSE timeout for user: {}", userId);
            sseEmitter.complete();
        });

        // 设置错误回调
        sseEmitter.onError((ex) -> {
            log.error("SSE error for user: {}", userId, ex);
            sseEmitter.complete();
        });

        try {
            tipsService.getTips(conversationId, sseEmitter, userId);
        } catch (Exception e) {
            log.error("处理SSE请求失败", e);
            sseEmitter.completeWithError(e);
        }

        return sseEmitter;
    }
}
