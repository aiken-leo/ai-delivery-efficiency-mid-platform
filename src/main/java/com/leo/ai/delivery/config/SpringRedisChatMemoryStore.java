package com.leo.ai.delivery.config;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于 Spring Data Redis 的对话记忆存储。
 * <p>
 * 复用已正确完成 AUTH 的 {@link StringRedisTemplate}，避免 LangChain4j
 * {@code RedisChatMemoryStore} 在「仅 requirepass」的 Redis 上无法兼容的问题。
 */
public class SpringRedisChatMemoryStore implements ChatMemoryStore {

    private static final String KEY_PREFIX = "chat_memory:";

    private final StringRedisTemplate redisTemplate;
    private final Duration ttl;

    public SpringRedisChatMemoryStore(StringRedisTemplate redisTemplate, long ttlSeconds) {
        this.redisTemplate = redisTemplate;
        this.ttl = ttlSeconds > 0 ? Duration.ofSeconds(ttlSeconds) : Duration.ZERO;
    }

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        String json = redisTemplate.opsForValue().get(toKey(memoryId));
        if (!StringUtils.hasText(json)) {
            return new ArrayList<>();
        }
        return ChatMessageDeserializer.messagesFromJson(json);
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        String key = toKey(memoryId);
        String json = ChatMessageSerializer.messagesToJson(messages);
        if (ttl.isZero() || ttl.isNegative()) {
            redisTemplate.opsForValue().set(key, json);
        } else {
            redisTemplate.opsForValue().set(key, json, ttl);
        }
    }

    @Override
    public void deleteMessages(Object memoryId) {
        redisTemplate.delete(toKey(memoryId));
    }

    private String toKey(Object memoryId) {
        if (memoryId == null || !StringUtils.hasText(memoryId.toString())) {
            throw new IllegalArgumentException("memoryId cannot be null or empty");
        }
        return KEY_PREFIX + memoryId;
    }
}
