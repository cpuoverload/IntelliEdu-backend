package com.team6.intellieduscoringservice.ai;

import dev.ai4j.openai4j.OpenAiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "openai")
public class OpenAiConfig {
    private String token;

    @Bean
    public OpenAiClient openAiClient() {
        return OpenAiClient.builder().openAiApiKey(token).build();
    }

}
