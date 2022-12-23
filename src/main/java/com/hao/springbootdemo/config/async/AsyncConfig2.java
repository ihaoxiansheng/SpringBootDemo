package com.hao.springbootdemo.config.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * 异步、线程池配置
 *
 * @author xu.liang
 * @since 2022/11/4 10:34
 */
@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig2 implements AsyncConfigurer, AsyncUncaughtExceptionHandler {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.initialize();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        return executor;
    }

    /**
     * 异步异常处理器，用来处理返回void的场景
     */
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        // System.out.println("Exception message - " + ex.getMessage());
        // System.out.println("Method name - " + method.getName());
        // for (Object param : params) {
        //     System.out.println("Parameter value - " + param);
        // }
        log.error("[handleUncaughtException][method({})，params({})，发生异常]", method.getName(), params, ex);
    }


}
