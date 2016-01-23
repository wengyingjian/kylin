/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.datasource.factory;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 2.0 15/11/3
 * @since 2.0
 */
@Component
@ConfigurationProperties(prefix = "datasource")
@PropertySource("classpath:datasource.properties")
@EnableConfigurationProperties(DatasourceProperties.class)
public class DatasourceProperties {

    private DB master;
    private DB slave;

    public DB getMaster() {
        return master;
    }

    public void setMaster(DB master) {
        this.master = master;
    }

    public DB getSlave() {
        return slave;
    }

    public void setSlave(DB slave) {
        this.slave = slave;
    }

    public static class DB {
        private String host;
        private int    port;
        private String db;
        private String username;
        private String password;
        private String mappersPath;
        private int    maxActive;
        private int    minIdle;
        private int    maxIdle;
        private int    validationInterval = 30000;
        private int    validationQueryTimeout = 5;
        private int    timeBetweenEvictionRunsMillis = 1800000;
        private int    initialSize;
        private int    maxWait = 10000;
        private int    removeAbandonedTimeout = 60;
        private int    minEvictableIdleTimeMillis = 30000;

        public String getMappersPath() {
            return mappersPath;
        }

        public void setMappersPath(String mappersPath) {
            this.mappersPath = mappersPath;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getDb() {
            return db;
        }

        public void setDb(String db) {
            this.db = db;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getMaxActive() {
            return maxActive;
        }

        public void setMaxActive(int maxActive) {
            this.maxActive = maxActive;
        }

        public int getMinIdle() {
            return minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public int getMaxIdle() {
            return maxIdle;
        }

        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }

        public int getValidationInterval() {
            return validationInterval;
        }

        public void setValidationInterval(int validationInterval) {
            this.validationInterval = validationInterval;
        }

        public int getValidationQueryTimeout() {
            return validationQueryTimeout;
        }

        public void setValidationQueryTimeout(int validationQueryTimeout) {
            this.validationQueryTimeout = validationQueryTimeout;
        }

        public int getTimeBetweenEvictionRunsMillis() {
            return timeBetweenEvictionRunsMillis;
        }

        public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
            this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
        }

        public int getInitialSize() {
            return initialSize;
        }

        public void setInitialSize(int initialSize) {
            this.initialSize = initialSize;
        }

        public int getMaxWait() {
            return maxWait;
        }

        public void setMaxWait(int maxWait) {
            this.maxWait = maxWait;
        }

        public int getRemoveAbandonedTimeout() {
            return removeAbandonedTimeout;
        }

        public void setRemoveAbandonedTimeout(int removeAbandonedTimeout) {
            this.removeAbandonedTimeout = removeAbandonedTimeout;
        }

        public int getMinEvictableIdleTimeMillis() {
            return minEvictableIdleTimeMillis;
        }

        public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
            this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
        }
    }

}
