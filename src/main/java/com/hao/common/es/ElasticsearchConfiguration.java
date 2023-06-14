package com.hao.common.es;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * rest client 初始化配置
 * 用于对restClient链接es的信息设置
 * 在使用是可通过注入restHighLevelClient即可
 */
@Configuration
@Slf4j
public class ElasticsearchConfiguration {

    @Resource
    private EsConfig esConfig;

    /**
     * ES 认证相关配置
     *
     * @return
     */
    @Bean
    public RequestOptions requestOptions() {
        if (StringUtils.isNotBlank(esConfig.getUserName()) && StringUtils.isNotBlank(esConfig.getPassword())) {
            // 设置es的用户名和密码
            RequestOptions.Builder requestOptions = RequestOptions.DEFAULT.toBuilder();
            String auth = "Basic " + Base64Utils.encodeToString((esConfig.getUserName() + ":" + esConfig.getPassword()).getBytes());
            requestOptions.addHeader("Authorization", auth);
            return requestOptions.build();
        }
        // 未设置账号密码时，返回默认的
        return RequestOptions.DEFAULT;
    }

    @Bean(name = "restHighLevelClient")
    public RestHighLevelClient restHighLevelClient() {
        log.info("es初始化连接中");
        // 拆分地址
        List<HttpHost> hostLists = new ArrayList<>();
        String nodes = esConfig.getNodes();
        String protocol = esConfig.getProtocol();
        int connectTimeout = esConfig.getConnectTimeout();
        int socketTimeout = esConfig.getSocketTimeout();
        int connectionRequestTimeout = esConfig.getConnectionRequestTimeout();
        int maxConnectNum = esConfig.getMaxConnectNum();
        int maxConnectPerRoute = esConfig.getMaxConnectPerRoute();
        String userName = esConfig.getUserName();
        String password = esConfig.getPassword();
        log.info("es连接信息：{}", nodes);
        String[] hostList = nodes.split(",");
        for (String addr : hostList) {
            String host = addr.split(":")[0];
            String port = addr.split(":")[1];
            hostLists.add(new HttpHost(host, Integer.parseInt(port), protocol));
        }
        // 转换成 HttpHost 数组
        HttpHost[] httpHost = hostLists.toArray(new HttpHost[]{});
        // 构建连接对象
        RestClientBuilder builder = RestClient.builder(httpHost);
        //开始设置用户名和密码
        if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
            builder.setHttpClientConfigCallback(f -> f.setDefaultCredentialsProvider(credentialsProvider));
        }
        // 异步连接延时配置
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(connectTimeout);
            requestConfigBuilder.setSocketTimeout(socketTimeout);
            requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeout);
            return requestConfigBuilder;
        });
        // 异步连接数配置
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(maxConnectNum);
            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
            return httpClientBuilder;
        });
        return new RestHighLevelClient(builder);
    }
}
