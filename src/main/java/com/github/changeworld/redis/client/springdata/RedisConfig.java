package com.github.changeworld.redis.client.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author changeworld
 */
@EnableAutoConfiguration
@EnableConfigurationProperties({RedisProperties.class})
public class RedisConfig {
    LettuceConnectionFactory connectionFactory;

    @Bean
    @Autowired
    RedisConnectionFactory redisConnectionFactory(RedisProperties properties) {
        if (properties.getCluster() != null) {
            new RedisClusterConfiguration(properties.getCluster().getNodes());
            connectionFactory = new LettuceConnectionFactory(
                    new RedisClusterConfiguration(properties.getCluster().getNodes())
            );
        } else {
            connectionFactory = new LettuceConnectionFactory(properties.getHost(),
                    properties.getPort());
        }
        if (properties.getPassword() != null) connectionFactory.setPassword(properties.getPassword());
        return connectionFactory;
    }

    @Bean
    @Autowired
    RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
