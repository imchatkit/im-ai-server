package com.imai.ai;


import com.imai.ai.config.LlmProperties;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 根据聊天内容生成回复
 *
 * @author wei
 * @date 2025/2/14 14:17
 */
@Slf4j
public class ReplyAiMemoryChain {

    private static final InMemoryChatMemoryStore memoryStore = new InMemoryChatMemoryStore();

    /**
     * 继续回复聊天（SSE流式响应版本）
     *
     * @param userMsg 聊天内容json
     * @param userId  用户ID
     */
    public static void chat(String userMsg, Long userId, SseEmitter emitter, LlmProperties llmProperties) {

        ChatMemory chatMemory = MessageWindowChatMemory.builder()
            .maxMessages(50)
            .id(userId.toString())
            .chatMemoryStore(memoryStore)
            .build();

        // 创建流式聊天模型
        OpenAiStreamingChatModel chatModel = OpenAiStreamingChatModel.builder()
//            .apiKey(llmProperties.getApiKey())
//            .modelName(LLMConfig.MODEL_NAME)
//            .baseUrl(LLMConfig.BASE_URL)
            .maxTokens(4000)
            .strictJsonSchema(true)
            .temperature(0.45)
            .topP(0.8)
            .frequencyPenalty(0.0)
            .logResponses(true)
            .logRequests(true)
            .build();

        Assistant assistant = AiServices.builder(Assistant.class)
            .streamingChatLanguageModel(chatModel)
            .chatMemoryProvider(memoryId -> {
                // 直接使用为该用户创建的chatMemory
                return chatMemory;
            })
            .build();

        TokenStream tokenStream = assistant.chat(userId, userMsg);

        // 流接收
        tokenStream.onPartialResponse(partialResponse -> {
            System.out.print(partialResponse);
            try {
                emitter.send(SseEmitter.event()
                    .name("message")
                    .data(partialResponse));
            } catch (IOException e) {
                log.error("发送SSE消息失败", e);
            }
        });

        // 错误
        tokenStream.onError(error -> {
            log.error("获取AI回复出错", error);
            try {
                emitter.completeWithError(error);
            } catch (Exception e) {
                log.error("关闭SSE连接失败", e);
            }
        });

        // 处理完成事件
        tokenStream.onCompleteResponse(response -> {
            log.info("\n----onCompleteResponse-Chat completed:\n {}", response);
            try {
                emitter.complete();
            } catch (Exception e) {
                log.error("关闭SSE连接失败", e);
            }
        });

        tokenStream.start();
    }

    /**
     * 根据聊天内容生成回复（SSE流式响应版本）
     *
     * @param msgJson 聊天内容json
     * @param userId  用户ID
     */
    public static void replySse(String msgJson, Long userId, SseEmitter emitter) {

        // 创建一个持久化的 ChatMemory 只用于保存对话
        ChatMemory persistentChatMemory = MessageWindowChatMemory.builder()
            .maxMessages(50)
            .id(userId.toString())
            .chatMemoryStore(memoryStore)
            .build();

        // 提示语
        String template = getTemplate();

        // 提示词模板stache语法，变量用
        PromptTemplate promptTemplate = PromptTemplate.from(template);
        Map<String, Object> variables = new HashMap<>();
        variables.put("msgJson", msgJson); // 替换变量

        Prompt prompt = promptTemplate.apply(variables);

        // 创建流式聊天模型
        OpenAiStreamingChatModel chatModel = OpenAiStreamingChatModel.builder()
//            .apiKey(LLMConfig.API_KEY)
//            .modelName(LLMConfig.MODEL_NAME)
//            .baseUrl(LLMConfig.BASE_URL)
            .maxTokens(8000)
            .strictJsonSchema(true)
            .temperature(0.5)
            .topP(0.8)
            .frequencyPenalty(0.0)
            .logResponses(true)
            .logRequests(true)
            .build();

        // 发送完整对话历史获取回复
        log.info("---开始发送对话历史获取AI回复:prompt:\n{}", prompt.text());

        // 使用流式API接收响应
        chatModel.chat(prompt.text(), new StreamingChatResponseHandler() {
            private final StringBuilder responseBuilder = new StringBuilder();

            @Override
            public void onPartialResponse(String partialResponse) {

                try {
                    responseBuilder.append(partialResponse);
                    emitter.send(SseEmitter.event()
                        .name("message")
                        .data(partialResponse));
                    // System.out.println("AI回复:");
                    System.out.print(partialResponse);
                } catch (IOException e) {
                    log.error("发送SSE消息失败", e);
                }
            }

            @Override
            public void onCompleteResponse(ChatResponse response) {

                // 将对话保存到持久化的 ChatMemory
                persistentChatMemory.add(UserMessage.from(prompt.text()));
                persistentChatMemory.add(AiMessage.from(response.toString()));
                log.info("---AI回复完成:\n" + response);
                try {
                    emitter.complete();
                } catch (Exception e) {
                    log.error("关闭SSE连接失败", e);
                }
            }

            @Override
            public void onError(Throwable error) {
                log.error("获取AI回复出错", error);
                try {
                    emitter.completeWithError(error);
                } catch (Exception e) {
                    log.error("关闭SSE连接失败", e);
                }
            }
        });
    }

    /**
     * /**
     * 获取提示语模板
     *
     * @return
     */
    private static String getTemplate() {
        String template;
        try {
            template = new String(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("prompt/msg_reply_prompt_v3.txt")
                .readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("读取提示词文件失败", e);
            throw new RuntimeException(e);
        }
        return template;
    }
}
