/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.rpc.server.config;

import com.wengyingjian.kylin.rpc.server.interceptor.RemoteInvocationMonitorInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

/**
 *
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 1.0 15/11/16
 * @since 1.0
 */
@Configuration
public class InterceptorConf {

    @Bean
    @ConditionalOnMissingBean(name = "remoteInvocationMonitorInterceptor")
    public MethodInterceptor remoteInvocationMonitorInterceptor() throws UnknownHostException {
        return new RemoteInvocationMonitorInterceptor();
    }

}
