package com.hao.config.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 项目启动后初始化操作
 * <p>
 * 使用@Bean注解和initMethod、destroyMethod属性：在Spring配置类中，使用@Bean注解创建Bean，并通过initMethod和destroyMethod属性指定初始化和销毁的方法:
 * <p>
 * 1、实现InitializingBean和DisposableBean接口 {@link MyBean}
 * <p>
 * 2、使用自定义的初始化和销毁方法 {@link MyBean2}
 * <p>
 * 实现可以不依赖javax.annotation.*包的情况下实现Bean的初始化和销毁逻辑
 *
 * @author xu.liang
 * @since 2023/6/13 15:43
 */
@Configuration
public class MyConfig {

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    public MyBean myBean() {
        return new MyBean();
    }

}
