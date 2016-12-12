package com.github.changeworld.redis.client.springdata;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * @author changeworld
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { RedisConfig.class })
@TestPropertySource("application-lettuce-cluster-test.properties")
public class LettuceClusterTest {
    static final String FOO = "foo";
    static final String BAR = "bar";

    @Test
    public void shouldReturnTrue() {
        assertSame(FOO, FOO);
    }

    /*
     * Embedded Redis 0.6 supports only the Redis Version 2.8.19.
     * However, Redis 3.0 or greater is required for the Redis Cluster function.
     * So, the following works only with Redis Version 3.x.
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Before
    public void setUp() {
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "FLUSHED";
            }
        });
    }

    @Test
    public void testRedisCluster() {
        redisTemplate.opsForValue().set(FOO, BAR);
        assertEquals(BAR, redisTemplate.opsForValue().get(FOO));
    }
     */
}
