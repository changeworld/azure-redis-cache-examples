package com.github.changeworld.redis.client.springdata;

import java.io.IOException;

import com.github.changeworld.redis.client.BaseClient;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisShardInfo;

/**
 * @author changeworld
 */
public class Redis implements BaseClient {
    private RedisTemplate<Object, Object> redisTemplate;

    public Redis(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Redis(String host, int port) {
        redisTemplate(new JedisConnectionFactory(new JedisShardInfo(host, port)));
    }

    public void redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
    }

    public void set(String key, String value) throws IOException {
        redisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) throws IOException {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void del(String key) throws IOException {
        redisTemplate.delete(key);
    }
}
