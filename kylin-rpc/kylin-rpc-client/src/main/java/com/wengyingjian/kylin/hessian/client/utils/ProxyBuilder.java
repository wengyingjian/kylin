/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.hessian.client.utils;


import com.wengyingjian.kylin.hessian.client.ServiceUrlSelector;

/**
 *
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 1.0 15/12/3
 * @since 1.0
 */
public class ProxyBuilder {

    public static Object buildProxyService(String serviceUrl, String serviceExportName, Class clazz) {
        return HessianProxyUtil.buildRemotingService(serviceUrl, serviceExportName, clazz);
    }

    public static Object buildProxyService(ServiceUrlSelector serviceUrlSelector, String serviceName, String serviceExportName, Class clazz) {
        return HessianProxyUtil.buildRemotingService(serviceUrlSelector, serviceName, serviceExportName, clazz);
    }

}
