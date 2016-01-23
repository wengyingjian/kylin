/*
 * Copyright 2015 © 59store.com.
 *
 * RemotingServiceConf.java
 *
 */
package com.wengyingjian.kylin.rpc.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

/**
 * Created by shanren on 7/17/15.
 */
@Configuration
public class RemotingServiceConf {

    public static String REMOTING_SERVICE_HANDLER_MAPPING_NAME = "remotingServiceHandlerMapping";

    @Bean
    public SimpleUrlHandlerMapping remotingServiceHandlerMapping() {
        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
        simpleUrlHandlerMapping.setOrder(2);
        return simpleUrlHandlerMapping;
    }

}
