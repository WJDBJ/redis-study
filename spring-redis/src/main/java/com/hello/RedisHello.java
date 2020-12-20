package com.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @author XJ
 */
@Component
public class RedisHello {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void hello(){
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("test1","hello spring-redis");
        System.out.println("value = " + valueOperations.get("test1"));
    }
}
