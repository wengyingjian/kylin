/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.hessian.client;

import com.caucho.hessian.HessianException;
import com.caucho.hessian.client.*;
import com.caucho.hessian.io.SerializerFactory;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.remoting.RemoteConnectFailureException;
import org.springframework.remoting.RemoteLookupFailureException;
import org.springframework.remoting.RemoteProxyFailureException;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:chenyb@59store.com">山人</a>
 * @version 1.0 15/12/3
 * @since 1.0
 */
public class KylinHessianClientInterceptor extends UrlSelectorBasedRemoteAccessor implements MethodInterceptor {

    private Map<String, Object> hessianProxyMap = new ConcurrentHashMap<>();
    private HessianProxyFactory proxyFactory    = new HessianProxyFactory();

    /**
     * Set the HessianProxyFactory instance to use.
     * If not specified, a default HessianProxyFactory will be created.
     * <p>Allows to use an externally configured factory instance,
     * in particular a custom HessianProxyFactory subclass.
     */
    public void setProxyFactory(HessianProxyFactory proxyFactory) {
        this.proxyFactory = (proxyFactory != null ? proxyFactory : new HessianProxyFactory());
    }

    /**
     * Specify the Hessian SerializerFactory to use.
     * <p>This will typically be passed in as an inner bean definition
     * of type {@code com.caucho.hessian.io.SerializerFactory},
     * with custom bean property values applied.
     */
    public void setSerializerFactory(SerializerFactory serializerFactory) {
        this.proxyFactory.setSerializerFactory(serializerFactory);
    }

    /**
     * Set whether to send the Java collection type for each serialized
     * collection. Default is "true".
     */
    public void setSendCollectionType(boolean sendCollectionType) {
        this.proxyFactory.getSerializerFactory().setSendCollectionType(sendCollectionType);
    }

    /**
     * Set whether to allow non-serializable types as Hessian arguments
     * and return values. Default is "true".
     */
    public void setAllowNonSerializable(boolean allowNonSerializable) {
        this.proxyFactory.getSerializerFactory().setAllowNonSerializable(allowNonSerializable);
    }

    /**
     * Set whether overloaded methods should be enabled for remote invocations.
     * Default is "false".
     *
     * @see com.caucho.hessian.client.HessianProxyFactory#setOverloadEnabled
     */
    public void setOverloadEnabled(boolean overloadEnabled) {
        this.proxyFactory.setOverloadEnabled(overloadEnabled);
    }

    /**
     * Set the username that this factory should use to access the remote service.
     * Default is none.
     * <p>The username will be sent by Hessian via HTTP Basic Authentication.
     *
     * @see com.caucho.hessian.client.HessianProxyFactory#setUser
     */
    public void setUsername(String username) {
        this.proxyFactory.setUser(username);
    }

    /**
     * Set the password that this factory should use to access the remote service.
     * Default is none.
     * <p>The password will be sent by Hessian via HTTP Basic Authentication.
     *
     * @see com.caucho.hessian.client.HessianProxyFactory#setPassword
     */
    public void setPassword(String password) {
        this.proxyFactory.setPassword(password);
    }

    /**
     * Set whether Hessian's debug mode should be enabled.
     * Default is "false".
     *
     * @see com.caucho.hessian.client.HessianProxyFactory#setDebug
     */
    public void setDebug(boolean debug) {
        this.proxyFactory.setDebug(debug);
    }

    /**
     * Set whether to use a chunked post for sending a Hessian request.
     *
     * @see com.caucho.hessian.client.HessianProxyFactory#setChunkedPost
     */
    public void setChunkedPost(boolean chunkedPost) {
        this.proxyFactory.setChunkedPost(chunkedPost);
    }

    /**
     * Specify a custom HessianConnectionFactory to use for the Hessian client.
     */
    public void setConnectionFactory(HessianConnectionFactory connectionFactory) {
        this.proxyFactory.setConnectionFactory(connectionFactory);
    }

    /**
     * Set the socket connect timeout to use for the Hessian client.
     *
     * @see com.caucho.hessian.client.HessianProxyFactory#setConnectTimeout
     */
    public void setConnectTimeout(long timeout) {
        this.proxyFactory.setConnectTimeout(timeout);
    }

    /**
     * Set the timeout to use when waiting for a reply from the Hessian service.
     *
     * @see com.caucho.hessian.client.HessianProxyFactory#setReadTimeout
     */
    public void setReadTimeout(long timeout) {
        this.proxyFactory.setReadTimeout(timeout);
    }

    /**
     * Set whether version 2 of the Hessian protocol should be used for
     * parsing requests and replies. Default is "false".
     *
     * @see com.caucho.hessian.client.HessianProxyFactory#setHessian2Request
     */
    public void setHessian2(boolean hessian2) {
        this.proxyFactory.setHessian2Request(hessian2);
        this.proxyFactory.setHessian2Reply(hessian2);
    }

    /**
     * Set whether version 2 of the Hessian protocol should be used for
     * parsing requests. Default is "false".
     *
     * @see com.caucho.hessian.client.HessianProxyFactory#setHessian2Request
     */
    public void setHessian2Request(boolean hessian2) {
        this.proxyFactory.setHessian2Request(hessian2);
    }

    /**
     * Set whether version 2 of the Hessian protocol should be used for
     * parsing replies. Default is "false".
     *
     * @see com.caucho.hessian.client.HessianProxyFactory#setHessian2Reply
     */
    public void setHessian2Reply(boolean hessian2) {
        this.proxyFactory.setHessian2Reply(hessian2);
    }

    /**
     * Create the Hessian proxy that is wrapped by this interceptor.
     *
     * @param proxyFactory the proxy factory to use
     * @return the Hessian proxy
     * @throws MalformedURLException if thrown by the proxy factory
     * @see com.caucho.hessian.client.HessianProxyFactory#create
     */
    protected Object createHessianProxy(HessianProxyFactory proxyFactory) throws MalformedURLException {
        Assert.notNull(getServiceInterface(), "'serviceInterface' is required");
        return proxyFactory.create(getServiceInterface(), getServiceUrl(), getBeanClassLoader());
    }

    /**
     * Convert the given Hessian access exception to an appropriate
     * Spring RemoteAccessException.
     *
     * @param ex the exception to convert
     * @return the RemoteAccessException to throw
     */
    protected RemoteAccessException convertHessianAccessException(Throwable ex) {
        if (ex instanceof HessianConnectionException || ex instanceof ConnectException) {
            return new RemoteConnectFailureException(
                    "Cannot connect to Hessian remote service at [" + getServiceUrl() + "]", ex);
        } else {
            return new RemoteAccessException(
                    "Cannot access Hessian remote service at [" + getServiceUrl() + "]", ex);
        }
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Object hessianProxy = null;
        String url = null;

        try {
            url = this.getServiceUrl();
            hessianProxy = hessianProxyMap.get(url);
            if (hessianProxy == null) {
                hessianProxy = createHessianProxy(this.proxyFactory);
                hessianProxyMap.put(url, hessianProxy);
            }
        } catch (MalformedURLException ex) {
            throw new RemoteLookupFailureException("Service URL [" + getServiceUrl() + "] is invalid", ex);
        }

        if (hessianProxy == null) {
            throw new IllegalStateException("HessianProxy init fail, service url : " + url);
        }

        ClassLoader originalClassLoader = overrideThreadContextClassLoader();
        try {
            return invocation.getMethod().invoke(hessianProxy, invocation.getArguments());
        } catch (InvocationTargetException ex) {
            Throwable targetEx = ex.getTargetException();
            // Hessian 4.0 check: another layer of InvocationTargetException.
            if (targetEx instanceof InvocationTargetException) {
                targetEx = ((InvocationTargetException) targetEx).getTargetException();
            }
            if (targetEx instanceof HessianConnectionException) {
                throw convertHessianAccessException(targetEx);
            } else if (targetEx instanceof HessianException || targetEx instanceof HessianRuntimeException) {
                Throwable cause = targetEx.getCause();
                throw convertHessianAccessException(cause != null ? cause : targetEx);
            } else if (targetEx instanceof UndeclaredThrowableException) {
                UndeclaredThrowableException utex = (UndeclaredThrowableException) targetEx;
                throw convertHessianAccessException(utex.getUndeclaredThrowable());
            } else {
                throw targetEx;
            }
        } catch (Throwable ex) {
            throw new RemoteProxyFailureException(
                    "Failed to invoke Hessian proxy for remote service [" + getServiceUrl() + "]", ex);
        } finally {
            resetThreadContextClassLoader(originalClassLoader);
        }
    }

}
