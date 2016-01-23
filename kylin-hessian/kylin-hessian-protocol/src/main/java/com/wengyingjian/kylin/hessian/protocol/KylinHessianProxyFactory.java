/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.hessian.protocol;

import com.caucho.hessian.client.*;
import com.caucho.hessian.io.*;

import javax.naming.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

/**
 * hessian客户端工厂类
 *
 * @author <a href="mailto:zhongc@59store.com">士兵</a>
 * @version 1.0 15/12/29
 * @since 1.0
 */
public class KylinHessianProxyFactory extends HessianProxyFactory{

    private String _basicAuth;

    /**
     * 创建一个代理工厂
     */
    public KylinHessianProxyFactory() {
        super();
    }

    /**
     * 创建一个代理工厂
     */
    public KylinHessianProxyFactory(ClassLoader loader) {
        super(loader);
    }

    /**
     * 设置用户
     */
    public void setUser(String user)
    {
        super.setUser(user);
        _basicAuth = null;
    }

    /**
     * 设置密码
     */
    public void setPassword(String password)
    {
        super.setPassword(password);
        _basicAuth = null;
    }

    public String getBasicAuth()
    {
        if (_basicAuth != null)
            return _basicAuth;

        return super.getBasicAuth();
    }

    /**
     * hessian请求连接时，设置连接工厂
     * to the Hessian service.
     */
    public void setConnectionFactory(HessianConnectionFactory factory)
    {
        super.setConnectionFactory(factory);
    }

    /**
     * 返回一个HTTP request的连接工厂.
     */
    public HessianConnectionFactory getConnectionFactory()
    {
        return super.getConnectionFactory();
    }

    /**
     * Sets the debug
     */
    public void setDebug(boolean isDebug)
    {
        super.setDebug(isDebug);
    }

    /**
     * Gets the debug
     */
    public boolean isDebug()
    {
        return super.isDebug();
    }

    /**
     * Returns true if overloaded methods are allowed (using mangling)
     */
    public boolean isOverloadEnabled()
    {
        return super.isOverloadEnabled();
    }

    /**
     * set true if overloaded methods are allowed (using mangling)
     */
    public void setOverloadEnabled(boolean isOverloadEnabled)
    {
        super.setOverloadEnabled(isOverloadEnabled);
    }

    /**
     * Set true if should use chunked encoding on the request.
     */
    public void setChunkedPost(boolean isChunked)
    {
        super.setChunkedPost(isChunked);
    }

    /**
     * Set true if should use chunked encoding on the request.
     */
    public boolean isChunkedPost()
    {
        return super.isChunkedPost();
    }

    /**
     * The socket timeout on requests in milliseconds.
     */
    public long getReadTimeout()
    {
        return super.getReadTimeout();
    }

    /**
     * The socket timeout on requests in milliseconds.
     */
    public void setReadTimeout(long timeout)
    {
        super.setReadTimeout(timeout);
    }

    /**
     * The socket connection timeout in milliseconds.
     */
    public long getConnectTimeout()
    {
        return super.getConnectTimeout();
    }

    /**
     * The socket connect timeout in milliseconds.
     */
    public void setConnectTimeout(long timeout)
    {
        super.setConnectTimeout(timeout);
    }

    /**
     * True if the proxy can read Hessian 2 responses.
     */
    public void setHessian2Reply(boolean isHessian2)
    {
        super.setHessian2Reply(isHessian2);
    }

    /**
     * True if the proxy should send Hessian 2 requests.
     */
    public void setHessian2Request(boolean isHessian2)
    {
        super.setHessian2Request(isHessian2);
    }

    /**
     * Returns the remote resolver.
     */
    public HessianRemoteResolver getRemoteResolver()
    {
        return super.getRemoteResolver();
    }

    /**
     * Sets the serializer factory.
     */
    public void setSerializerFactory(SerializerFactory factory)
    {
        super.setSerializerFactory(factory);
    }

    /**
     * Gets the serializer factory.
     */
    public SerializerFactory getSerializerFactory()
    {
        return super.getSerializerFactory();
    }

    /**
     * 重载该方法，采用 httpClient实现hessian远程调用
     *
     * @return
     */
    protected HessianConnectionFactory createHessianConnectionFactory()
    {
        String className
                = System.getProperty(HessianConnectionFactory.class.getName());

        HessianConnectionFactory factory = null;

        try {
            if (className != null) {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();

                Class<?> cl = Class.forName(className, false, loader);

                factory = (HessianConnectionFactory) cl.newInstance();

                return factory;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new HessianHttpClientFactory();
    }

    /**
     * Creates a new proxy with the specified URL.  The API class uses
     * the java.api.class value from _hessian_
     *
     * @param url the URL where the client object is located.
     *
     * @return a proxy to the object with the specified interface.
     */
    public Object create(String url)
            throws MalformedURLException, ClassNotFoundException
    {
        return super.create(url);
    }

    /**
     * Creates a new proxy with the specified URL.  The returned object
     * is a proxy with the interface specified by api.
     *
     * <pre>
     * String url = "http://localhost:8080/ejb/hello");
     * HelloHome hello = (HelloHome) factory.create(HelloHome.class, url);
     * </pre>
     *
     * @param api the interface the proxy class needs to implement
     * @param urlName the URL where the client object is located.
     *
     * @return a proxy to the object with the specified interface.
     */
    public Object create(Class api, String urlName)
            throws MalformedURLException
    {
        return super.create(api, urlName);
    }

    /**
     * spring链接接口
     * 调用该方法产生 hessianProxy的代理实例
     */
    public Object create(Class<?> api, String urlName, ClassLoader loader)
            throws MalformedURLException
    {
        return super.create(api, urlName, loader);
    }

    /**
     * 重载该方法，实现类采用KylinHessianProxy
     */
    public Object create(Class<?> api, URL url, ClassLoader loader)
    {
        if (api == null)
            throw new NullPointerException("api must not be null for KylinHessianProxyFactory.create()");
        InvocationHandler handler = null;

        handler = new KylinHessianProxy(url, this, api);

        return Proxy.newProxyInstance(loader,
                new Class[]{api,
                        HessianRemoteObject.class},
                handler);
    }

    public AbstractHessianInput getHessianInput(InputStream is)
    {
        return super.getHessian2Input(is);
    }

    public AbstractHessianInput getHessian1Input(InputStream is)
    {
        return super.getHessian1Input(is);
    }

    public AbstractHessianInput getHessian2Input(InputStream is)
    {
        return super.getHessian2Input(is);
    }

    public AbstractHessianOutput getHessianOutput(OutputStream os)
    {
        return super.getHessianOutput(os);
    }

    /**
     * JNDI object factory so the proxy can be used as a resource.
     */
    public Object getObjectInstance(Object obj, Name name,
                                    Context nameCtx, Hashtable<?,?> environment)
            throws Exception
    {
        return super.getObjectInstance(obj, name, nameCtx, environment);
    }
}
