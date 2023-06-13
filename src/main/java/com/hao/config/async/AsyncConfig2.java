package com.hao.config.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

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
        // 配置线程池中的线程的名称前缀(方便排查问题)
        executor.setThreadNamePrefix("async-scheduler-service-");

        // 配置线程拒绝策略
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // 1.CallerRunsPolicy:不在新线程中执行任务，而是由调用者所在的线程来执行。
        //   "该策略既不会抛弃任务，也不会抛出异常，而是将任务回推到调用者。"顾名思义，在饱和的情况下，调用者会执行该任务（而不是由多线程执行）
        // 2.AbortPolicy:拒绝策略，直接拒绝抛出异常
        // 3.DiscardPolicy:丢弃任务，但是不抛出异常。可以配合这种模式进行自定义的处理方式
        // 4.DiscardOldestPolicy:丢弃队列最早的未处理任务，然后重新尝试执行任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

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
