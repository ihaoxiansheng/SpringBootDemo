package com.hao.springbootdemo.test.demo1;


import org.junit.Test;

import java.util.concurrent.*;

/**
 * 方法超时异常处理
 *
 * @author xu.liang
 * @since 2022/12/12 17:55
 */
public class Test1213Test {
    @Test
    public void test() throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<String> future = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getResult();
            }
        });
        // 或者 FutureTask<String> future = new FutureTask<>(this::getResult);
        executor.execute(future);
        try {
            // 指定时间内不返回结果就会报 TimeoutException
            String result = future.get(1100, TimeUnit.MILLISECONDS);
            System.out.println("时间充足");
            System.out.println("result = " + result);
        } catch (TimeoutException ex) {
            System.out.println("执行时间超时");
            ex.printStackTrace();
        } catch (Exception e) {
            System.out.println("方法执行出错");
        }
        // 正在执行的任务立即停止，没有执行的不再执行
        executor.shutdownNow();
        // 正在执行任务继续执行，没有执行的不再执行
        // executor.shutdown();
    }

    /**
     * 要测试的方法
     */
    public String getResult() {
        try {
            // 毫秒
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

}
