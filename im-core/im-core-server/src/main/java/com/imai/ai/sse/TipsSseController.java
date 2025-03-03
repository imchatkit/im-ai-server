package com.imai.ai.sse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wei
 * @date 2025/3/3 15:43
 */
@RestController
public class TipsSseController {

    @GetMapping("/tips")
    public void getTips(Long conversationId) {

    }
}
