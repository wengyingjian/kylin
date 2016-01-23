/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.rpc.monitor.statd;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Statsd.
 * 
 * @author <a href="mailto:zhuzm@59store.com">天河</a>
 * @version 2.0 2015年10月26日
 * @since 2.0
 */
@ConfigurationProperties(prefix = "statsd")
public class StatsdProperties {

    private String  host;

    private int     port;

    /** 是否支持在一个数据包中传输多个度量数据. */
    private boolean multiMetrics;

    /** 数据包缓冲区大小，单位：字节. */
    private short   bufferSize;

    /** 数据包缓冲区刷新间隔，单位：毫秒. */
    private long    flushPeriod;

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the multiMetrics
     */
    public boolean isMultiMetrics() {
        return multiMetrics;
    }

    /**
     * @param multiMetrics the multiMetrics to set
     */
    public void setMultiMetrics(boolean multiMetrics) {
        this.multiMetrics = multiMetrics;
    }

    /**
     * @return the bufferSize
     */
    public short getBufferSize() {
        return bufferSize;
    }

    /**
     * @param bufferSize the bufferSize to set
     */
    public void setBufferSize(short bufferSize) {
        this.bufferSize = bufferSize;
    }

    /**
     * @return the flushPeriod
     */
    public long getFlushPeriod() {
        return flushPeriod;
    }

    /**
     * @param flushPeriod the flushPeriod to set
     */
    public void setFlushPeriod(long flushPeriod) {
        this.flushPeriod = flushPeriod;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
