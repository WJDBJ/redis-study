package com;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;

import java.time.Duration;

/**
 * @author XJ
 */
public class RedisUtil {
    private static StatefulRedisConnection connection;

    static {
        RedisURI redisURI = RedisURI.Builder
                .redis("127.0.0.1")
                .withPort(6379)
                .withPassword("xj")
                .withDatabase(0)
                .withTimeout(Duration.ofSeconds(5))
                .build();
        connection = RedisClient.create(redisURI).connect();
    }

    public static RedisCommands getCommands() {
        return connection.sync();
    }

    public static RedisAsyncCommands getAsyncCommands(){
        return connection.async();
    }

    public static RedisReactiveCommands getReactiveCommands() {
        return connection.reactive();
    }
}
