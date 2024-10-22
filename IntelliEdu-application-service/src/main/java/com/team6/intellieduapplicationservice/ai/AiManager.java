package com.team6.intellieduapplicationservice.ai;

import dev.ai4j.openai4j.OpenAiClient;
import dev.ai4j.openai4j.chat.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Component
public class AiManager {

    @Resource
    private OpenAiClient client;


    /**
     * 通用请求
     *
     * @param messageList
     * @param temperature
     * @return
     */
    public String doRequest(List<Message> messageList, double temperature) {
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("gpt-4o-mini")
                .temperature(temperature)
                .messages(messageList)
                .build();

        ChatCompletionResponse chatCompletionResponse = client.chatCompletion(chatCompletionRequest).execute();

        return chatCompletionResponse.choices().get(0).delta().content();
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
                .model("gpt-4o-mini")
                .stream(true)
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


}
