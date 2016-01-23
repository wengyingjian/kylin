/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.hessian.client;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.remoting.support.RemoteAccessor;

/**
 *
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 1.0 15/12/3
 * @since 1.0
 */
public class UrlSelectorBasedRemoteAccessor extends RemoteAccessor implements InitializingBean {

    private ServiceUrlSelector serviceUrlSelector;
    private String exportName;
    private String serviceName;

    public UrlSelectorBasedRemoteAccessor() {
    }

    public ServiceUrlSelector getServiceUrlSelector() {
        return serviceUrlSelector;
    }

    public void setServiceUrlSelector(ServiceUrlSelector serviceUrlSelector) {
        this.serviceUrlSelector = serviceUrlSelector;
    }

    public String getExportName() {
        return exportName;
    }

    public void setExportName(String exportName) {
        this.exportName = exportName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void afterPropertiesSet() {
        if(this.getServiceUrlSelector() == null) {
            throw new IllegalArgumentException("Property \'serviceUrlSelector\' is required");
        }
        if(this.getExportName() == null) {
            throw new IllegalArgumentException("Property \'exportName\' is required");
        }
        if(this.getServiceName() == null) {
            throw new IllegalArgumentException("Property \'serviceName\' is required");
        }
    }

    public String getServiceUrl() {
        String serviceUrl = getServiceUrlSelector().selectUrl(serviceName);
        if (!serviceUrl.endsWith("/")) {
            serviceUrl = serviceUrl + "/";
        }
        return serviceUrl + exportName;
    }
}
