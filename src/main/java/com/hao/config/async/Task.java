package com.hao.config.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author xu.liang
 * @since 2022/11/4 16:23
 */
@Slf4j
@Component
public class Task {

    public static Random random = new Random();

    // @Async("taskExecutor")
    public void doTaskOne() throws Exception {
        log.info("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info(">>>>>>>>>>线程1:【{}】执行成功<<<<<<<<<<", Thread.currentThread().getName());
        log.info("完成任务一，耗时：" + (end - start) + "毫秒");
    }

    @Async("taskExecutorDistribute")
    public void doTaskTwo() throws Exception {
        log.info("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info(">>>>>>>>>>线程2:【{}】执行成功<<<<<<<<<<", Thread.currentThread().getName());
        log.info("完成任务二，耗时：" + (end - start) + "毫秒");
    }

    @Async("taskExecutorDistribute")
    public void doTaskThree() throws Exception {
        log.info("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info(">>>>>>>>>>线程3:【{}】执行成功<<<<<<<<<<", Thread.currentThread().getName());
        log.info("完成任务三，耗时：" + (end - start) + "毫秒");
    }


}

