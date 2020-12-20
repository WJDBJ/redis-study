package com;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * @author XJ
 */
public class RedisLettuceHelloWorld {
    public static void main(String[] args) {
        lettuceHelloWorld();
    }

    private static void lettuceHelloWorld() {
        RedisCommands<String,String> commands = RedisUtil.getCommands();
        commands.set("test","hello redis");
        commands.expire("test",600);
        String value = commands.get("test");
        System.out.println("value = " + value);
    }
}
