/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.datasource.factory;

import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 2.0 15/11/5
 * @since 2.0
 */
public class DBHelper {

    private static String url    = "jdbc:mysql://%s:%d/%s?useUnicode=true&characterEncoding=utf8";
    private static String driver = "com.mysql.jdbc.Driver";
    public static int DEFAULT_MAX_IDLE = 10;
    public static int DEFAULT_MIN_IDLE = 2;
    public static int DEFAULT_MAX_ACTIVE = 20;

    public static PoolProperties buildPoolProperties(DatasourceProperties.DB dbProperties) {
        if (dbProperties == null) {
            return null;
        }
        if (dbProperties.getMaxIdle() <= 0) {
            dbProperties.setMaxIdle(DEFAULT_MAX_IDLE);
        }
        if (dbProperties.getMaxActive() <= 0) {
            dbProperties.setMaxActive(DEFAULT_MAX_ACTIVE);
        }
        if (dbProperties.getMinIdle() <= 0) {
            dbProperties.setMinIdle(DEFAULT_MIN_IDLE);
        }
        if (dbProperties.getInitialSize() <= 0) {
            dbProperties.setInitialSize(dbProperties.getMinIdle());
        }
        PoolProperties p = new PoolProperties();
        p.setUrl(String.format(url, dbProperties.getHost(), dbProperties.getPort(), dbProperties.getDb()));
        p.setDriverClassName(driver);
        p.setUsername(dbProperties.getUsername());
        p.setPassword(dbProperties.getPassword());
        p.setTestWhileIdle(true);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setValidationInterval(dbProperties.getValidationInterval());
        p.setValidationQueryTimeout(dbProperties.getValidationQueryTimeout());
        p.setTimeBetweenEvictionRunsMillis(dbProperties.getTimeBetweenEvictionRunsMillis());
        p.setMaxActive(dbProperties.getMaxActive());
        p.setInitialSize(dbProperties.getInitialSize());
        p.setMaxWait(dbProperties.getMaxWait());
        p.setRemoveAbandonedTimeout(dbProperties.getRemoveAbandonedTimeout());
        p.setMinEvictableIdleTimeMillis(dbProperties.getMinEvictableIdleTimeMillis());
        p.setInitSQL("set names utf8mb4");
        p.setMinIdle(dbProperties.getMinIdle());
        p.setMaxIdle(dbProperties.getMaxIdle());
        return p;
    }

}
