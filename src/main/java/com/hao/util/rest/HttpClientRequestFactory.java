package com.hao.util.rest;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.net.URI;

/**
 * @author xu.liang
 * @since 2024/4/12 10:36
 */
public class HttpClientRequestFactory extends HttpComponentsClientHttpRequestFactory {

    public static ThreadLocal<Integer> socketTimeoutThreadLocal = new ThreadLocal<>();

    public HttpClientRequestFactory(HttpClient httpClient) {
        super(httpClient);
    }

    @Override
    protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
        HttpContext context = HttpClientContext.create();
        RequestConfig config = createRequestConfig(getHttpClient());

        // 从ThreadLocal中获取超时时间，并设置到context中
        Integer socketTimeout = socketTimeoutThreadLocal.get();
        if (null != socketTimeout) {
            RequestConfig.Builder builder = RequestConfig.copy(config);
            builder.setSocketTimeout(socketTimeout);
            builder.setConnectTimeout(socketTimeout);
            builder.setConnectionRequestTimeout(socketTimeout);
            config = builder.build();
        }
        context.setAttribute(HttpClientContext.REQUEST_CONFIG, config);
        return context;
    }
}
