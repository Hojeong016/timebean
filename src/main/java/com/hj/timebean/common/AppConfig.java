package com.hj.timebean.common;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ChatClient chatClient() {
        return new ChatClient() {
            @Override
            public String call(String message) {
                return ChatClient.super.call(message);
            }

            @Override
            public ChatResponse call(Prompt prompt) {
                return null;
            }
        };
    }
}
