package com.hao.springbootdemo.test.demo4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author xu.liang
 * @since 2023/4/1 16:03
 */
public class runAsyncTest {

    public static void main(String[] args) {
        // 获取当前线程名
        String name = Thread.currentThread().getName();
        System.out.println(name);
        // 1111111111111111111111
        System.out.println("11111");
        // 方法
        CompletableFuture.runAsync(() -> {
            try {
                // 方法
                System.out.println("22222");
                String name2 = Thread.currentThread().getName();
                System.out.println(name2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        List<String> list = new ArrayList<>();


    }
}
