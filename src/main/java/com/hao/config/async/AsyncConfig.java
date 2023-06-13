package com.hao.config.async;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.reflect.Method;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 异步、线程池配置
 *
 * @author xu.liang
 * @since 2022/11/4 10:34
 */
@Configuration
// 检测Spring的@Async注释
@EnableAsync
@Slf4j
public class AsyncConfig implements AsyncUncaughtExceptionHandler {

    private static final ThreadPoolExecutor THREAD_POOL;

    static {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("produce-pool-%d").build();
        // ThreadPoolExecutor参数：核心线程数，最大线程数，多余空闲线程的存活时间，时间单位，有界序列大小，线程工厂，拒绝策略丢弃任务并抛出异常
        THREAD_POOL = new ThreadPoolExecutor(4, 8, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(512),
                threadFactory, new ThreadPoolExecutor.AbortPolicy());
        // 是否允许核心线程超时，即开启线程池的动态增长和缩小
        THREAD_POOL.allowCoreThreadTimeOut(true);
    }

    @Bean("taskExecutorDistribute")
    public Executor asyncExecutor() {
        return THREAD_POOL;
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

    /**
     * 使用1：方法上@Async注解使用 无返回值
     */
    @Async
    public void asyncMethodWithVoidReturnType() {
        System.out.println("Execute method asynchronously. " + Thread.currentThread().getName());
    }

    /**
     * 使用2：方法上@Async注解使用 有返回值
     */
    @Async
    public Future<String> asyncMethodWithReturnType() {
        System.out.println("Execute method asynchronously - " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            return new AsyncResult<String>("hello world！！！");
        } catch (InterruptedException e) {
            //
        }
        return null;
    }

    /**
     * CompletableFuture.runAsync(() -> {
     * // 业务逻辑
     * }, THREAD_POOL);
     * 使用3：在这里开启一个异步任务，提交给线程池，runAsync()方法没有返回值，需要有返回值的可使用supplyAsync()方法
     */
    public void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture.runAsync(() -> {
            int result = 0;
            for (int i = 0; i <= 100; i++) {
                result += i;
            }
            System.out.println(result);
        }, THREAD_POOL);

        // 关于CompletableFuture的其他相关用法
        // 1 get()获取异步的结果，get方法是一个阻塞式等待的方法，也即get方法会等待异步任务的完成
        AtomicInteger sum22 = new AtomicInteger();
        CompletableFuture<AtomicInteger> completableFuture22 = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i <= 100; i++) {
                sum22.addAndGet(i);
            }
            return sum22;
        }, THREAD_POOL);
        // 获取异步结果
        AtomicInteger integer2 = completableFuture22.get();

        // 2 allOf : 等待所有任务完成完成
        AtomicInteger sum = new AtomicInteger();
        AtomicInteger sum2 = new AtomicInteger();
        CompletableFuture<AtomicInteger> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i <= 100; i++) {
                sum.addAndGet(i);
            }
            return sum;
        }, THREAD_POOL);

        CompletableFuture<AtomicInteger> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i <= 100; i++) {
                sum2.addAndGet(i);
            }
            return sum2;
        }, THREAD_POOL);
        AtomicInteger integer = completableFuture2.get();
        // allOf : 等待所有任务完成完成，注意get方法，是阻塞式等待，等待上面的异步任务都完成
        CompletableFuture.allOf(completableFuture1,completableFuture2).get();
        // 获取异步结果
        AtomicInteger atomicInteger1 = completableFuture1.get();
        AtomicInteger atomicInteger2 = completableFuture2.get();
        System.out.println("结果是--->"+atomicInteger1.addAndGet(atomicInteger2.intValue()));

        // 3 异步任务完成时，whenComplete，exceptionally
        CompletableFuture<AtomicInteger> completableFuture3 = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i <= 10; i++) {
                sum2.addAndGet(i);
            }
            return sum2;
        }, THREAD_POOL).whenComplete((res, exception) -> {
            // 当出现异常，可以拿到异常信息，但是无法修改返回数据
            System.out.println("结果是：" + res + ",异常：" + exception);
        }).exceptionally(throwable -> {
            // 可以感知异常，同时返回默认值
            return new AtomicInteger(10);
        });

        // 4 handle，方法完成后的后续处理
        CompletableFuture<Integer> completableFuture4 = CompletableFuture.supplyAsync(() -> {
            int i = 10 / 2;
            return i;
        }, THREAD_POOL).handle((res, throwable) -> {
            // res 为结果，throwable 为异常
            if (res != null) {
                return res * 2;
            }
            if (throwable != null) {
                return -1;
            }
            return 0;
        });
        System.out.println("completableFuture4--结果是："+completableFuture4.get());

        // 5 异步任务串行化 thenAcceptAsync 可以接收上一步获取的结果，但是无返回值，thenApplyAsync 可以接收上一步获取的结果，有返回值
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            int i = 10 / 2;
            return i;
        }, THREAD_POOL).thenApplyAsync(res -> {
            // res为上一步的结果
            return res * 2;
        }, THREAD_POOL).thenAcceptAsync((res) -> {
            System.out.println("hello ...thenAcceptAsync");
        }, THREAD_POOL);



    }


}
