/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.hessian.client.utils;

import com.wengyingjian.kylin.hessian.client.KylinHessianProxyFactoryBean;
import com.wengyingjian.kylin.hessian.client.ServiceUrlSelector;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 *
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 1.0 15/11/13
 * @since 1.0
 */
public class HessianProxyUtil {

    public static Object buildRemotingService(String serviceUrl, String serviceExportName, Class clazz) {
        HessianProxyFactoryBean hessianProxyFactoryBean = new HessianProxyFactoryBean();
        hessianProxyFactoryBean.setOverloadEnabled(true);
        hessianProxyFactoryBean.setServiceInterface(clazz);
        if (!serviceUrl.endsWith("/")) {
            serviceUrl = serviceUrl + "/";
        }
        hessianProxyFactoryBean.setServiceUrl(serviceUrl + serviceExportName);
        hessianProxyFactoryBean.afterPropertiesSet();
        return hessianProxyFactoryBean.getObject();
    }

    public static Object buildRemotingService(ServiceUrlSelector serviceUrlSelector, String serviceName, String serviceExportName, Class clazz) {
        KylinHessianProxyFactoryBean hessianProxyFactoryBean = new KylinHessianProxyFactoryBean();
        hessianProxyFactoryBean.setOverloadEnabled(true);
        hessianProxyFactoryBean.setServiceInterface(clazz);
        hessianProxyFactoryBean.setServiceName(serviceName);
        hessianProxyFactoryBean.setExportName(serviceExportName);
        hessianProxyFactoryBean.setServiceUrlSelector(serviceUrlSelector);
        hessianProxyFactoryBean.afterPropertiesSet();
        return hessianProxyFactoryBean.getObject();
    }

}
