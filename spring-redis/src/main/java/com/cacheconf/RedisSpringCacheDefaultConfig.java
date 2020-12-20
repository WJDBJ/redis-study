package com.cacheconf;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;

/**
 * @author XJ
 *整个spring容器只能有一个CachingConfigurerSupport的继承类
 * @EnableCaching 启用缓存
 */
@Configuration
@EnableCaching
@ComponentScan("com.cache")
public class RedisSpringCacheDefaultConfig extends CachingConfigurerSupport {
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
        RedisStandaloneConfiguration redisStandaloneConfiguration
                = lettuceConnectionFactory.getStandaloneConfiguration();
        redisStandaloneConfiguration.setPort(6379);
        //设置密码
        redisStandaloneConfiguration.setPassword("xj");
        redisStandaloneConfiguration.setHostName("127.0.0.1");
        redisStandaloneConfiguration.setDatabase(0);
        return lettuceConnectionFactory;
    }

    /**
     * 这个bean必须加上，否则CacheManager不会被注册，详情见@EnableCaching注解
     */
    @Bean
    @Override
    public CacheManager cacheManager() {
        RedisCacheConfiguration redisCacheConfiguration
                = RedisCacheConfiguration
                    .defaultCacheConfig()
                    .entryTtl(Duration.ofSeconds(30))
                    .disableCachingNullValues();

        return RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(redisCacheConfiguration).build();
    }
}
