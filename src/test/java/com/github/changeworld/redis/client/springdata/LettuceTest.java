package com.github.changeworld.redis.client.springdata;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.embedded.RedisServer;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author changeworld
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { RedisConfig.class })
@TestPropertySource("application-lettuce-test.properties")
public class LettuceTest {
    static final String FOO = "foo";
    static final String BAR = "bar";
    static final int PORT = 16379;
    static final RedisServer REDIS_SERVER = newRedisServer();

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    static RedisServer newRedisServer() {
        try {
            return new RedisServer(PORT);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @BeforeClass
    public static void beforeClass() {
        try {
            REDIS_SERVER.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void afterClass() {
        REDIS_SERVER.stop();
    }

    @Test
    public void shouldLettuceCanGetAfterSet() {
        try {
            Redis client = new Redis(redisTemplate);
            client.set(FOO, BAR);
            assertEquals(BAR, client.get(FOO));
            client.del(FOO);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
