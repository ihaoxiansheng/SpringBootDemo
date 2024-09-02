package com.hao.test.year.demo2024.demo5;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 简单使用ConcurrentHashMap的示例，在多线程环境下安全地进行插入和获取操作
 *
 * @author xu.liang
 * @since 2024/5/14 10:15
 */
public class ConcurrentHashMapExample {
    private final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    public void putValue(String key, String value) {
        map.put(key, value);
    }

    public String getValue(String key) {
        return map.get(key);
    }

    public static void main(String[] args) {
        ConcurrentHashMapExample example = new ConcurrentHashMapExample();

        // 使用 Runnable 来模拟多线程环境
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                example.putValue("key" + i, "value" + i);
            }
        };

        // 启动 10 个线程并发执行任务
        for (int i = 0; i < 10; i++) {
            new Thread(task).start();
        }

        // 等待所有线程完成操作
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 打印出 map 的内容
        example.map.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
