 package com.hao.util.rest;

 import lombok.extern.slf4j.Slf4j;
 import org.apache.http.config.Registry;
 import org.apache.http.config.RegistryBuilder;
 import org.apache.http.conn.socket.ConnectionSocketFactory;
 import org.apache.http.conn.socket.PlainConnectionSocketFactory;
 import org.apache.http.conn.ssl.NoopHostnameVerifier;
 import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
 import org.apache.http.conn.ssl.TrustStrategy;
 import org.apache.http.impl.client.CloseableHttpClient;
 import org.apache.http.impl.client.HttpClientBuilder;
 import org.apache.http.impl.client.HttpClients;
 import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
 import org.apache.http.ssl.SSLContextBuilder;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.context.annotation.DependsOn;
 import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
 import org.springframework.http.converter.StringHttpMessageConverter;
 import org.springframework.web.client.DefaultResponseErrorHandler;
 import org.springframework.web.client.RestTemplate;

 import javax.net.ssl.HostnameVerifier;
 import javax.net.ssl.SSLContext;
 import java.nio.charset.StandardCharsets;
 import java.security.KeyManagementException;
 import java.security.KeyStoreException;
 import java.security.NoSuchAlgorithmException;
 import java.security.cert.CertificateException;
 import java.security.cert.X509Certificate;

 /**
  * @author xu.liang
  * @since 2024/4/12 10:36
  */
 @Configuration
 @Slf4j
 public class RestTemplateConfig {

     @Bean
     @DependsOn("clientHttpRequestFactory")
     public RestTemplate restTemplate() {
         RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
         restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
         restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
         return restTemplate;
     }

     @Bean
     public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
         log.info("初始化HTTP连接池开始");
         try {
             HttpClientBuilder httpClientBuilder = HttpClients.custom();
             SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                 public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                     return true;
                 }
             }).build();
             httpClientBuilder.setSSLContext(sslContext);
             HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
             SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
                     hostnameVerifier);
             // 注册http和https请求
             Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                     .register("http", PlainConnectionSocketFactory.getSocketFactory())
                     .register("https", sslConnectionSocketFactory).build();
             // 开始设置连接池
             PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(
                     socketFactoryRegistry);
             poolingHttpClientConnectionManager.setMaxTotal(500); // 最大连接数500
             poolingHttpClientConnectionManager.setDefaultMaxPerRoute(100); // 同路由并发数100
             httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
 //            httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, true)); // 重试次数

             CloseableHttpClient httpClient = httpClientBuilder.build();
             // httpClient连接配置
             HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpClientRequestFactory(httpClient);
             // 连接超时
             clientHttpRequestFactory.setConnectTimeout(20000);
             // 数据读取超时时间
             clientHttpRequestFactory.setReadTimeout(30000);
             // 连接不够用的等待时间
             clientHttpRequestFactory.setConnectionRequestTimeout(20000);
             return clientHttpRequestFactory;
         } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
             log.error("初始化HTTP连接池错误", e);
         }
         return null;
     }
 }
