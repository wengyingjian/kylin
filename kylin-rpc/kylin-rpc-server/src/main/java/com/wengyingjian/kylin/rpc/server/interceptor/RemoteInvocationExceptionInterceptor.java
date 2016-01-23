/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.rpc.server.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import com.wengyingjian.kylin.common.exception.BaseException ;
import com.wengyingjian.kylin.common.model.Result ;
/**
 * 捕捉远程调用异常.
 * 
 * @author <a href="mailto:zhuzm@59store.com">天河</a>
 * @version 1.1 2015年10月6日
 * @since 1.1
 */
public class RemoteInvocationExceptionInterceptor implements MethodInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RemoteInvocationExceptionInterceptor.class);

    private final String        exporterName;

    /**
     * Create a new RemoteInvocationExceptionInterceptor.
     */
    public RemoteInvocationExceptionInterceptor() {
        this.exporterName = "";
    }

    /**
     * Create a new RemoteInvocationExceptionInterceptor.
     * 
     * @param exporterName the name of the remote exporter (to be used as context information in log messages)
     */
    public RemoteInvocationExceptionInterceptor(String exporterName) {
        this.exporterName = exporterName;
    }

    /**
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            return invocation.proceed();
        } catch (Throwable ex) {
            logger.error("Processing of {} remote call resulted in exception: {}, arguments: {}", exporterName,
                    ClassUtils.getQualifiedMethodName(invocation.getMethod()), invocation.getArguments(), ex);

            return buildExceptionResult(ex);
        }
    }

    /**
     * 构建异常结果对象.
     * 
     * @param ex {@link Throwable}
     * @return {@link Result}
     */
    private Result<Object> buildExceptionResult(Throwable ex) {
        Result<Object> result = new Result<Object>();
        if (ex instanceof BaseException) {
            BaseException be = (BaseException) ex;
            result.setStatus(be.getStatus());
            result.setMsg(be.getMessage());
        } else {
            result.setStatus(-1);
            result.setMsg("未知异常");
        }

        return result;
    }

}
