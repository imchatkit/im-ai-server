package org.dromara.demo;

import com.imai.ImCoreServerApplication;
import com.imai.ai.config.LlmProperties;
import com.imai.ai.service.impl.TipsServiceImpl;
import com.imai.core.service.IImMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@SpringBootTest(classes = ImCoreServerApplication.class)
class TipsServiceImplTest {

    @Autowired
    private TipsServiceImpl tipsService;

    @Autowired
    private IImMessageService imMessageService;

    @Autowired
    private LlmProperties llmProperties;

    private SseEmitter sseEmitter;

    @BeforeEach
    void setUp() {
        sseEmitter = new SseEmitter();
    }

    @Test
    void getTips_WithRealDatabase_ShouldProcessMessages() throws Exception {
        // 使用真实的会话ID和用户ID
        Long conversationId = 1899034792928772098L; // 替换为实际的会话ID
        Long userId = 230154870053440L;       // 替换为实际的用户ID

        // 执行测试
        tipsService.getTips(conversationId, sseEmitter, userId);

    }
}
