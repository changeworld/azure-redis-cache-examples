package com.github.changeworld.redis.client.lettuce;

import com.github.changeworld.redis.client.BaseClient;
import com.lambdaworks.redis.RedisClusterConnection;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.cluster.RedisClusterClient;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.Set;

/**
 * @author changeworld
 */
public class Cluster implements BaseClient {
    private RedisClusterClient lettuceCluster;
    private RedisClusterConnection<String, String> connection;

    public Cluster(String host, int port, String password) {
        this.lettuceCluster = RedisClusterClient.create(RedisURI.Builder.redis(host).withPort(port).withPassword(password).build());
        this.connection = lettuceCluster.connectCluster();
    }

    public RedisClusterClient getRedisCluster() {
        return this.lettuceCluster;
    }

    public RedisClusterConnection getRedisClusterConnection() {
        return this.connection;
    }

    public void set(String key, String value) throws IOException {
        getRedisClusterConnection().set(key, value);
    }

    public String get(String key) throws IOException {
        return (String) getRedisClusterConnection().get(key);
    }

    public void del(String key) throws IOException {
        getRedisClusterConnection().del(key);
    }

    public void close() throws IOException {
        getRedisClusterConnection().close();
        getRedisCluster().shutdown();
    }
}
