package me.intelliedu.intellieducommon.ai;

import dev.ai4j.openai4j.OpenAiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAiConfig {
    @Value("${OPENAI_TOKEN}")
    private String token;

    @Value("${OPENAI_ORG_ID}")
    private String orgId;

    @Value("${OPENAI_TIMEOUT:60000}")
    private int timeout;


    @Bean
    public OpenAiClient openAiClient() {
        return OpenAiClient.builder().openAiApiKey(token).build();
    }

}
