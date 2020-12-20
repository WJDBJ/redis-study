package com.config;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

/**
 * @author XJ
 */
@Configuration
@EnableCaching
public class RedisSpringBootConfig implements RedisCacheManagerBuilderCustomizer {
    private RedisCacheConfiguration initRedisCacheConfiguration(Long l) {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        return cacheConfiguration.serializeKeysWith(fromSerializer(RedisSerializer.string()))
                .serializeValuesWith(fromSerializer(RedisSerializer.json()))
                .entryTtl(Duration.ofSeconds(l))
                .computePrefixWith(cacheName -> "projectName".concat(":").concat(cacheName).concat(":"));
    }

    @Override
    public void customize(RedisCacheManager.RedisCacheManagerBuilder builder) {
        Map<String,RedisCacheConfiguration> map = new HashMap<>(2);
        map.put("cache1",initRedisCacheConfiguration(1800L));
        map.put("cache2",initRedisCacheConfiguration(3600L));
        builder.withInitialCacheConfigurations(map);
    }
}

/*
@Configuration
@EnableCaching
public class RedisSpringBootConfig {
    private RedisCacheConfiguration initRedisCacheConfiguration(Long l) {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        return cacheConfiguration.serializeKeysWith(fromSerializer(RedisSerializer.string()))
                .serializeValuesWith(fromSerializer(RedisSerializer.json()))
                .entryTtl(Duration.ofSeconds(l))
                .computePrefixWith(cacheName -> "projectName".concat(":").concat(cacheName).concat(":"));
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        Map<String,RedisCacheConfiguration> map = new HashMap<>(2);
        map.put("cache1",initRedisCacheConfiguration(1800L));
        map.put("cache2",initRedisCacheConfiguration(3600L));
        return RedisCacheManager.builder(redisConnectionFactory)
                //.cacheDefaults() 不设置也会有默认缓存 详情RedisCacheManager
                .withInitialCacheConfigurations(map)
                .build();
    }
}*/