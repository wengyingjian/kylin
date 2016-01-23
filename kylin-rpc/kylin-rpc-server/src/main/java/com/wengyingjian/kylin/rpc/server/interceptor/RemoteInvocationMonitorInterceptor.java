/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.rpc.server.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 远程调用监控拦截器.
 * 
 * @author <a href="mailto:zhuzm@59store.com">天河</a>
 * @version 2.0 2015年10月26日
 * @since 2.0
 */
public class RemoteInvocationMonitorInterceptor implements MethodInterceptor {

    /**
     * @see MethodInterceptor#invoke(MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return invocation.proceed();
    }

}
