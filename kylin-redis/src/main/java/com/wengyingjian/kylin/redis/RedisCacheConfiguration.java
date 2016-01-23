/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.DefaultRedisCachePrefix;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis缓存配置.
 * 
 * @author <a href="mailto:zhuzm@59store.com">天河</a>
 * @version 1.0 2015年9月18日
 * @since 1.0
 */
@EnableCaching
@Configuration
@EnableConfigurationProperties(RedisExpireProperties.class)
public class RedisCacheConfiguration extends CachingConfigurerSupport {

    @Autowired
    private RedisExpireProperties expireProperties;

    @Bean
    public CacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        if (StringUtils.isNotBlank(expireProperties.getCachePrefix())) {
            String cachePrefix = "." + expireProperties.getCachePrefix() + ":";
            redisCacheManager.setCachePrefix(new DefaultRedisCachePrefix(cachePrefix));
            redisCacheManager.setUsePrefix(true);
        }

        redisCacheManager.setDefaultExpiration(expireProperties.getDefaultExpiration());
        return redisCacheManager;
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new RedisCommonErrorHandler();
    }
}
