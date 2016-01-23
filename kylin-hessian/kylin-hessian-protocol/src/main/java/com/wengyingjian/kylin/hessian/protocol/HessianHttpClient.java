/**
 * Copyright (c) 2015, 59store. All rights reserved.
 */
package com.wengyingjian.kylin.hessian.protocol;

import com.caucho.hessian.client.HessianConnection;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * 使用HttpClient实现hessian的远程调用
 *
 * @author <a href="mailto:zhongc@59store.com">士兵</a>
 * @version 1.0 15/12/16
 * @since 1.0
 */
public class HessianHttpClient implements HessianConnection {

    private          CloseableHttpClient _client;
    private          HttpPost            _request;
    private volatile HttpResponse        _response;

    private ByteArrayOutputStream _outputStream;

    public HessianHttpClient(URL url, CloseableHttpClient client) {
        _client = client;
        _request = new HttpPost(url.toString());
        _outputStream = new ByteArrayOutputStream();
    }

    @Override
    public void addHeader(String key, String value) {
        _request.setHeader(new BasicHeader(key, value));
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return this._outputStream;
    }

    @Override
    public void sendRequest() throws IOException {
        _request.setEntity(new ByteArrayEntity(_outputStream.toByteArray()));
        _response = _client.execute(_request);
    }

    @Override
    public int getStatusCode() {
        return _response == null || _response.getStatusLine() == null ? 0 : _response.getStatusLine().getStatusCode();
    }

    @Override
    public String getStatusMessage() {
        return _response == null || _response.getStatusLine() == null ? null : _response.getStatusLine().getReasonPhrase();
    }

    @Override
    public String getContentEncoding() {
        return null;
//        return _response == null || _response.getEntity() == null || _response.getEntity().getContentEncoding() == null? "deflate" : _response.getEntity().getContentEncoding().getValue();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return _response == null || _response.getEntity() == null ? null : _response.getEntity().getContent();
    }

    @Override
    public void close() throws IOException {
        _outputStream.close();
        if (_request != null)
            _request.abort();
    }

    @Override
    public void destroy() throws IOException {
    }
}
