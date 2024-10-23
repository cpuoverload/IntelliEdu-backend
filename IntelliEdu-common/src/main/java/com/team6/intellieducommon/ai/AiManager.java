package com.team6.intellieducommon.ai;

import dev.ai4j.openai4j.OpenAiClient;
import dev.ai4j.openai4j.chat.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

import static com.team6.intellieducommon.constant.Constant.MAX_TOKEN_LENGTH;


@Component
@Slf4j
public class AiManager {

    @Resource
    private OpenAiClient openAiClient;


    /**
     * 通用请求
     *
     * @param messageList
     * @param temperature
     * @return
     */
    public String doRequest(List<Message> messageList, double temperature) {
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("gpt-4o")
                .maxTokens(MAX_TOKEN_LENGTH)
                .temperature(temperature)
                .messages(messageList)
                .build();

        ChatCompletionResponse chatCompletionResponse = openAiClient.chatCompletion(chatCompletionRequest).execute();

        return chatCompletionResponse.choices().get(0).message().content();
    }

    public String doRequest(String systemMessage, String userMessage, double temperature) {
        List<Message> messages = new ArrayList<>();
        SystemMessage sysMessage = SystemMessage.from(systemMessage);
        UserMessage uMessage = UserMessage.from(userMessage);
        messages.add(sysMessage);
        messages.add(uMessage);
        return doRequest(messages, temperature);
    }

    /**
     * 通用流式请求
     *
     * @param temperature
     * @param messages
     * @return
     */
    public ChatCompletionRequest generalStreamRequest(double temperature, List<Message> messages) {
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("gpt-4o")
                .stream(true)
                .maxTokens(MAX_TOKEN_LENGTH)
                .temperature(temperature)
                .messages(messages)
                .build();

        return chatCompletionRequest;
    }

    /**
     * 通用流式请求，优化消息传递
     *
     * @param systemMessage
     * @param userMessage
     * @param temperature
     * @return
     */
    public ChatCompletionRequest generalStreamRequest(String systemMessage, String userMessage, double temperature) {
        List<Message> messages = new ArrayList<>();
        SystemMessage sysMessage = SystemMessage.from(systemMessage);
        UserMessage uMessage = UserMessage.from(userMessage);
        messages.add(sysMessage);
        messages.add(uMessage);
        return generalStreamRequest(temperature, messages);
    }


    public void executeChatCompletion(ChatCompletionRequest chatCompletionRequest, SseEmitter emitter, CompletableFuture<String> future) {
        StringBuilder contentBuilder = new StringBuilder();
        AtomicInteger flag = new AtomicInteger(0);

        openAiClient.chatCompletion(chatCompletionRequest)
                .onPartialResponse(response -> {
                    String message = response.choices().get(0).delta().content();
                    if (message != null) {
//                        message = message.replaceAll("\\s", "");
                        for (char c : message.toCharArray()) {
                            if (c == '{') {
                                flag.incrementAndGet();
                            }
                            if (flag.get() > 0) {
                                contentBuilder.append(c);
                            }
                            if (c == '}') {
                                flag.decrementAndGet();
                                if (flag.get() == 0) {
                                    try {
                                        emitter.send(contentBuilder.toString());
                                        contentBuilder.setLength(0);
                                    } catch (IOException e) {
                                        log.error("Error sending partial JSON object", e);
                                        emitter.completeWithError(e);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                })
                .onComplete(() -> {
                    future.complete(contentBuilder.toString());
                    emitter.complete();
                })
                .onError(throwable -> {
                    log.error("Error during chat completion", throwable);
                    future.completeExceptionally(throwable);
                    emitter.completeWithError(throwable);
                })
                .execute();

        future.whenComplete((response, throwable) -> {
            if (throwable != null) {
                log.error("Future completed with error", throwable);
                emitter.completeWithError(throwable);
            }
        });
    }


}
