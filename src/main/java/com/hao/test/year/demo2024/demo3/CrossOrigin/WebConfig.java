package com.hao.test.year.demo2024.demo3.CrossOrigin;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 方法1：在Controller加上@CrossOrigin
 * 方法2：本类
 *
 * @author xu.liang
 * @since 2024/3/1 09:18
 */
@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private CrossOriginIntercept crossOriginIntercept;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 不拦截的uri
        final String[] commonExclude = {"/static/**", "/error", "/files/**"};
        registry.addInterceptor(crossOriginIntercept).addPathPatterns("/**").excludePathPatterns(commonExclude);
    }

}
