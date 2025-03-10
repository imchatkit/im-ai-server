package com.imai.ai.sse;

import com.imai.ai.service.TipsService;
import org.dromara.common.satoken.utils.LoginHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * AI/提示
 * @author wei
 * @date 2025/3/3 15:43
 */
@RestController
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

        // 当前用户ID
        Long userId = LoginHelper.getUserId();

        SseEmitter sseEmitter = new SseEmitter();
        tipsService.getTips(conversationId, sseEmitter,   userId );
        return sseEmitter;
    }
}
