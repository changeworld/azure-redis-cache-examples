package com.github.changeworld.redis.client.spring;

import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.embedded.RedisServer;

import static org.junit.Assert.assertEquals;

/**
 * @author changeworld
 */
public class JedisTest {
    static final String FOO = "foo";
    static final String BAR = "bar";
    static final String HOST = "localhost";
    static final int PORT = 6379;
    static final String TYPE = "jedis";
    static final RedisServer REDIS_SERVER = newRedisServer();

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
    public void shouldJedisCanReadWrite() {
        try {
            DataRedis client = new DataRedis(HOST, PORT, null, TYPE, false);
            client.set(FOO, BAR);
            assertEquals(BAR, client.get(FOO));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
