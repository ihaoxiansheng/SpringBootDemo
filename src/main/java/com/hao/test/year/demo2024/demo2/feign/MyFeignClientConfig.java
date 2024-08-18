 package com.hao.test.year.demo2024.demo2.feign;

 import feign.Client;
 import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
 import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
 import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
 import org.springframework.context.annotation.Bean;
 import org.springframework.stereotype.Component;

 /**
 * 注册FeignClient
 *
 * @author xu.liang
 * @since 2024/3/7 13:43
 */
 @Component
 public class MyFeignClientConfig {

    public CachingSpringLoadBalancerFactory cachingLBClientFactory(
            SpringClientFactory factory) {
        return new CachingSpringLoadBalancerFactory(factory);
    }

    @Bean
    public Client feignClient(SpringClientFactory clientFactory) {
        CachingSpringLoadBalancerFactory bean = cachingLBClientFactory(clientFactory);
        return new LoadBalancerFeignClient(new MyFeignClient(null, null), bean, clientFactory);
    }

 }
