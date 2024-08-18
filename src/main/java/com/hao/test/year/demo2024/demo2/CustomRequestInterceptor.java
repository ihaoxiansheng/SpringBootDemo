package com.hao.test.year.demo2024.demo2;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Feign 请求拦截器<p>
 * openfeign设置全局header
 *
 * @author xu.liang
 * @since 2024/2/4 16:15
 */
@Component
@Slf4j
public class CustomRequestInterceptor implements RequestInterceptor {

    public CustomRequestInterceptor() {
        log.info("初始化 Feign 请求拦截器");
    }

    @Override
    public void apply(RequestTemplate template) {
        // 添加全局Header
//        template.header("Global-Header-Name", "Global-Header-Value");

        // 例如，如果您需要添加一个认证令牌，可以这样做：
        // template.header("Authorization", "Bearer" + authToken);

        log.info("开始feign调用：{}{}", template.feignTarget() == null ? "" : template.feignTarget().url(), template.path());
    }

}
