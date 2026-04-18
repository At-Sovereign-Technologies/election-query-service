package com.electoral.election_query_service.cache;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@ExtendWith(MockitoExtension.class)
class RedisCacheAdapterTest {

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private ValueOperations<String, Object> valueOperations;

    @InjectMocks
    private RedisCacheAdapter cache;

    @BeforeEach
    void setup() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    void shouldGetValueFromRedis() {
        when(valueOperations.get("key")).thenReturn("value");

        Object result = cache.get("key");

        assertEquals("value", result);
        verify(valueOperations).get("key");
    }

    @Test
    void shouldSetValueInRedisWithTTL() {
        cache.set("key", "value");

        verify(valueOperations)
                .set(eq("key"), eq("value"), eq(10L), eq(TimeUnit.MINUTES));
    }
}