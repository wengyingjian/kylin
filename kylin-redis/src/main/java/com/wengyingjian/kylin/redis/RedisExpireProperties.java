/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.redis;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * Redis超时属性.
 * 
 * @author <a href="mailto:zhuzm@59store.com">天河</a>
 * @version 1.0 2015年9月12日
 * @since 1.0
 */
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@ConfigurationProperties(prefix = "redis")
//@PropertySource("classpath:redis.properties")
public class RedisExpireProperties extends RedisProperties {

    private String cachePrefix;

    // 0 - never expire
    private long defaultExpiration = 0;

    /**
     * @return the cachePrefix
     */
    public String getCachePrefix() {
        return cachePrefix;
    }

    /**
     * @param cachePrefix the cachePrefix to set
     */
    public void setCachePrefix(String cachePrefix) {
        this.cachePrefix = cachePrefix;
    }

    /**
     * @return the defaultExpiration
     */
    public long getDefaultExpiration() {
        return defaultExpiration;
    }

    /**
     * @param defaultExpiration the defaultExpiration to set
     */
    public void setDefaultExpiration(long defaultExpiration) {
        this.defaultExpiration = defaultExpiration;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
