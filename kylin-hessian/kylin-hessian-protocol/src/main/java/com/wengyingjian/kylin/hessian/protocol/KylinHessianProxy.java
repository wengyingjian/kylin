/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.hessian.protocol;

import com.caucho.hessian.client.HessianConnection;
import com.caucho.hessian.client.HessianProxy;
import com.caucho.hessian.client.HessianProxyFactory;
import org.slf4j.MDC;

import java.net.URL;

/**
 * hessian proxy 代理类
 *
 * @author <a href="mailto:zhongc@59store.com">士兵</a>
 * @version 1.0 15/12/29
 * @since 1.0
 */
public class KylinHessianProxy extends HessianProxy{

    /**
     * Protected constructor for subclassing
     */
    protected KylinHessianProxy(URL url, HessianProxyFactory factory) {
        super(url, factory);
    }

    /**
     * Protected constructor for subclassing
     */
    protected KylinHessianProxy(URL url,
                                HessianProxyFactory factory,
                                Class<?> type) {
        super(url, factory, type);
    }

    /**
     * 重载该方法，添加requestId的传递
     */
    protected void addRequestHeaders(HessianConnection conn)
    {
        super.addRequestHeaders(conn);
        if(MDC.get("reqId") != null)
            conn.addHeader("request-id", MDC.get("reqId"));
    }
}
