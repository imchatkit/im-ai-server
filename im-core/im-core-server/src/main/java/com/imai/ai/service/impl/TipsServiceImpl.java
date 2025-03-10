package com.imai.ai.service.impl;

import com.imai.ai.ReplyAiMemoryChain;
import com.imai.ai.config.LlmProperties;
import com.imai.ai.service.TipsService;
import com.imai.core.domain.vo.ImMessageVo;
import com.imai.core.service.IImMessageService;

import lombok.extern.slf4j.Slf4j;

import org.dromara.common.json.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author wei
 * @date 2025/3/3 15:58
 */
@Service
@Slf4j
public class TipsServiceImpl implements TipsService {
    @Autowired
    private IImMessageService imMessageService;

    @Autowired
    private LlmProperties llmProperties;

    @Override
    public void getTips(Long conversationId, SseEmitter emitter, Long userId) {
        try {
            log.info("开始处理用户 {} 的SSE请求, conversationId: {}", userId, conversationId);

            List<ImMessageVo> messageVoList = imMessageService.queryListByFkConversationId(conversationId);
            if (messageVoList.isEmpty()) {
                emitter.send(SseEmitter.event().data("无聊天记录"));
                emitter.complete();
                return;
            }

            ArrayList<HashMap<String, String>> msgList = new ArrayList<>();
            for (ImMessageVo messageVo : messageVoList) {
                HashMap<String, String> map = new HashMap<>();
                map.put("sender", messageVo.getFkFromUserId().toString());
                map.put("content", messageVo.getMsgText());
                msgList.add(map);
            }

            String msgJson = JsonUtils.toJsonString(msgList);
            log.info("处理消息列表: {}", msgJson);

            ReplyAiMemoryChain.replySse(msgJson, userId, emitter, llmProperties);
        } catch (Exception e) {
            log.error("处理SSE消息失败", e);
            try {
                emitter.completeWithError(e);
            } catch (Exception ex) {
                log.error("关闭SSE连接失败", ex);
            }
        }
    }
}
