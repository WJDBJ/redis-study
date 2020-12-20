package com.pojo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author XJ
 */
public class Main {
    public static void main(String[] args) {
        //redisDefaultSerializer();
        redisJacksonSerializer();
    }

    public static void redisDefaultSerializer() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisPojoHello redisPojoHello = applicationContext.getBean(RedisPojoHello.class);
        redisPojoHello.hello();
    }

    public static void redisJacksonSerializer() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisSerializerConfig.class);
        RedisPojoHello redisPojoHello = applicationContext.getBean(RedisPojoHello.class);
        redisPojoHello.hello();
    }
}
