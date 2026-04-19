package com.electoral.election_query_service.cache;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RedisCacheAdapterTest {

    /*@Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private ValueOperations<String, Object> valueOperations;

    @Mock
    private ObjectMapper objectMapper; 

    @InjectMocks
    private RedisCacheAdapter cache;

    @BeforeEach
    void setup() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    void shouldGetValueFromRedis() {
        // dado
        when(valueOperations.get("key")).thenReturn("value");
        when(objectMapper.convertValue("value", String.class)).thenReturn("value");

        // cuando
        String result = cache.get("key", String.class);

        // entonces
        assertEquals("value", result);
        verify(valueOperations).get("key");
    }

    @Test
    void shouldSetValueInRedisWithTTL() {
        cache.set("key", "value");

        verify(valueOperations)
                .set(eq("key"), eq("value"), eq(10L), eq(TimeUnit.MINUTES));
    }*/
}