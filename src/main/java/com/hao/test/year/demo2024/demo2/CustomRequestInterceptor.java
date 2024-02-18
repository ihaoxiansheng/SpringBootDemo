package com.hao.test.year.demo2024.demo2;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 * openfeign设置全局header
 *
 * @author xu.liang
 * @since 2024/2/4 16:15
 */
@Component
public class CustomRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        // 添加全局Header
//        template.header("Global-Header-Name", "Global-Header-Value");

        // 例如，如果您需要添加一个认证令牌，可以这样做：
        // template.header("Authorization", "Bearer" + authToken);
    }

}
