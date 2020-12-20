package com.cache;

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
import org.springframework.data.redis.serializer.RedisSerializer;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XJ
 */
@Configuration
@ComponentScan("com.cache")
@EnableCaching
public class RedisSpringCacheMultiConfig extends CachingConfigurerSupport {
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
        RedisStandaloneConfiguration redisStandaloneConfiguration
                = lettuceConnectionFactory.getStandaloneConfiguration();
        redisStandaloneConfiguration.setPort(6379);
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
        Map<String,RedisCacheConfiguration> map = new HashMap<>(2);
        map.put("cache1",initRedisCacheConfiguration(1800L));
        map.put("cache2",initRedisCacheConfiguration(3600L));
        return RedisCacheManager.builder(redisConnectionFactory())
                //.cacheDefaults() 不设置也会有默认缓存 详情RedisCacheManager
                .withInitialCacheConfigurations(map)
                .build();
    }

    private RedisCacheConfiguration initRedisCacheConfiguration(Long l) {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        return cacheConfiguration.serializeKeysWith(fromSerializer(RedisSerializer.string()))
                .serializeValuesWith(fromSerializer(RedisSerializer.json()))
                .entryTtl(Duration.ofSeconds(l))
                .computePrefixWith(cacheName -> "projectName".concat(":").concat(cacheName).concat(":"));
    }
}
