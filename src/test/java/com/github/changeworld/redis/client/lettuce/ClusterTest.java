package com.github.changeworld.redis.client.lettuce;

import org.junit.After;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

/**
 * @author changeworld
 */
public class ClusterTest {
    private static Cluster cluster;
    private static final String FOO = "foo";
    private static final String BAR = "bar";
    // In this line, replace <host> with your host:
    private static final String HOST = "<host>";
    // In this line, replace 6379 with your port:
    private static final int PORT = 6379;
    // In this line, replace <key> with your access key:
    private static final String KEY = "<key>";

    @After
    public void after() throws IOException {
        if (cluster != null) {
            cluster.close();
        }
    }

    @Test
    public void shouldReturnTrue() {
        assertSame(FOO, FOO);
    }

    /*
     * Embedded Redis 0.6 supports only the Redis Version 2.8.19.
     * However, Redis 3.0 or greater is required for the Redis Cluster function.
     * So, the following works only with Redis Version 3.x.
    @Test
    public void shouldLettuceClusterCanGetAfterSet() {
        try {
            cluster = new Cluster(HOST,
                    PORT,
                    KEY);
            cluster.set(FOO, BAR);
            assertEquals(BAR, cluster.get(FOO));
            cluster.del(FOO);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
     */
}
