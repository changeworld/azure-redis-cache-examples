package com.github.changeworld.redis.client.jedis;

import com.github.changeworld.redis.client.BaseClient;
import java.io.IOException;
import redis.clients.jedis.Jedis;

/**
 * @author changeworld
 */
public class Client implements BaseClient {
    private Jedis jedis;

    public Client(Jedis jedis) {
        this.jedis = jedis;
    }

    public Jedis getRedisClient() {
        return this.jedis;
    }

    public void set(String key, String value) throws IOException {
        getRedisClient().set(key, value);
    }

    public String get(String key) throws IOException {
        return getRedisClient().get(key);
    }

    public void del(String key) throws IOException {
        getRedisClient().del(key);
    }

    public void close() {
        getRedisClient().close();
    }
}
