/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.rpc.protocol;

import com.caucho.hessian.client.HessianConnection;
import com.caucho.hessian.client.HessianConnectionFactory;
import com.caucho.hessian.client.HessianProxyFactory;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * httpClient工厂类
 *
 * @author <a href="mailto:zhongc@59store.com">士兵</a>
 * @version 1.0 15/12/16
 * @since 1.0
 */
public class HessianHttpClientFactory implements HessianConnectionFactory {
    private static final Logger log
            = Logger.getLogger(HessianHttpClientFactory.class.getName());

    private CloseableHttpClient httpClient;

    @Override
    public void setHessianProxyFactory(HessianProxyFactory factory) {
        long connectTimeout = factory.getConnectTimeout();
        long readTimeout = factory.getReadTimeout();
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout((int) connectTimeout)
                .setSocketTimeout((int) readTimeout)
                .build();
        httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();
    }

    /**
     * Opens a new or recycled connection to the HTTP server.
     */
    public HessianConnection open(URL url)
            throws IOException {
        if (log.isLoggable(Level.FINER))
            log.finer(this + " open(" + url + ")");
        return new HessianHttpClient(url, httpClient);
    }
}
