package com.hao.test.year.demo2024.demo2.Reflection;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CompletableFuture异步线程测试
 *
 * @author xu.liang
 * @since 2024/2/21 10:49
 */
@Slf4j
public class CompletableFutureTest {

    @SneakyThrows
    public static void main(String[] args) {

        // runAsync 与 supplyAsync 两者区别
        // runAsync方法不支持返回值
        // supplyAsync可以支持返回值
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> System.out.println("hello world"));
        System.out.println("future.get() = " + future.get());

        // 获取当前时间戳
        CompletableFuture<Long> future2 = CompletableFuture.supplyAsync(System::currentTimeMillis);
        System.out.println("future2.get() = " + future2.get());

        // Executor说明：如果方法存在executor参数，就使用executor执行任务，否则默认使用公用的ForkJoinPool.commonPool()作为执行异步任务的线程池。
        ExecutorService executor = Executors.newCachedThreadPool();
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> System.out.println("hello world2"), executor);
        System.out.println("future3.get() = " + future3.get());
        CompletableFuture<Long> future4 = CompletableFuture.supplyAsync(System::currentTimeMillis, executor);
        System.out.println("future4.get() = " + future4.get());

        try {
            Class<?> c = Class.forName("com.hao.test.year.demo2024.demo2.Reflection.CompletableFutureTest");
            Method m = c.getMethod("test1");
//            Object bean = SpringUtil.getBean(c);
            Object invoke = m.invoke(CompletableFutureTest.class.newInstance());
            System.out.println("invoke = " + invoke);
        } catch (Exception e) {
            log.error("调用失败：", e);
        }


    }

    public String test1() {
        System.out.println("111111111111111111111111");
        return "调用成功";
    }

}
