package com.github.changeworld.redis.client.springdata;

import java.io.IOException;

import com.github.changeworld.redis.client.BaseClient;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.DefaultLettucePool;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisShardInfo;

/**
 * @author changeworld
 */
public class Redis implements BaseClient {
    private RedisTemplate<Object, Object> redisTemplate;

    public Redis(String host, int port, String password, String type, boolean cluster) {
        if (type.equals("jedis")) {
            redisTemplate(new JedisConnectionFactory(new JedisShardInfo(host, port)));
        } else {
            lettuceConnectionFactory(defaultLettucePool(host, port));
        }
    }

    public DefaultLettucePool defaultLettucePool(String host, int port) {
        DefaultLettucePool defaultLettucePool = new DefaultLettucePool(host, port);
        defaultLettucePool.afterPropertiesSet();
        return defaultLettucePool;
    }

    public void lettuceConnectionFactory(DefaultLettucePool defaultLettucePool) {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(defaultLettucePool);
        lettuceConnectionFactory.afterPropertiesSet();
        lettuceConnectionFactory.setShareNativeConnection(true);
        redisTemplate(lettuceConnectionFactory);
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
