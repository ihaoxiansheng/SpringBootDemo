package com.hao;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * feign代理配置
 * <pre>
 * feign:
 *   okhttp:
 *     enabled: true
 *     proxy:
 *       host: 192.168.129.78
 *       port: 8443
 *       domains: 10.143.28.206
 * </pre>
 *
 * @author xu.liang
 * @since 2023/7/6 11:27
 */
@Configuration
@Slf4j
public class ProxyConfig {

    @Value("${feign.okhttp.proxy.host:}")
    private String proxyHost;

    @Value("${feign.okhttp.proxy.port:}")
    private Integer proxyPort;

    @Value("#{'${feign.okhttp.proxy.domains:}'.split(',')}")
    private Set<String> domainList;

    @Value("${feign.client.config.default.connectTimeout:600100}")
    private Integer connectTimeout;

    @Value("${feign.client.config.default.readTimeout:600010}")
    private Integer readTimeout;

    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                // 设置连接超时
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                // 设置读超时
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                // 设置写超时
                .writeTimeout(readTimeout, TimeUnit.MILLISECONDS)
                // 是否自动重连
                .retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool());
        if (StringUtils.isNotEmpty(proxyHost) || proxyPort != null) {
            // 配置代理
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
            List<Proxy> proxyList = new ArrayList<>(1);
            proxyList.add(proxy);
            builder.proxySelector(new ProxySelector() {
                @Override
                public List<Proxy> select(URI uri) {
                    log.info("feign uri:{}", uri);
                    if (uri == null || !domainList.contains(uri.getHost())) {
                        return Collections.singletonList(Proxy.NO_PROXY);
                    }
                    return proxyList;
                }

                @Override
                public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
                }
            });
        }
        // 构建OkHttpClient对象
        return builder.build();
    }

}
