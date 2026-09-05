package com.leo.ai.delivery.config;

import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Redis 持久化对话记忆
 */
@Configuration
public class RedisChatMemoryStoreConfig {

    @Bean
    public ChatMemoryStore chatMemoryStore(
            StringRedisTemplate stringRedisTemplate,
            @Value("${spring.data.redis.ttl:3600}") long ttl) {
        return new SpringRedisChatMemoryStore(stringRedisTemplate, ttl);
    }
}
