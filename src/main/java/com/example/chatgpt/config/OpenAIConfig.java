package com.example.chatgpt.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class OpenAIConfig {

    @Bean
    public ChatLanguageModel chatLanguageModel(Environment env) {
        return OpenAiChatModel.builder()
                .apiKey(env.getProperty("openai.api.key"))
                .modelName("gpt-4o-mini")
                .build();
    }
}