package com.hao.util;

import com.hao.util.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * 异步测试
 *
 * @author xu.liang
 * @since 2024/4/15 13:47
 */
@Slf4j
public class AsyncTest {

    /**
     * os：可能因为是main方法测试异步，控制台不打印异步的日志
     *
     * @param args args
     */
    public static void main(String[] args) {
        try {
            CompletableFuture.supplyAsync(() -> {

                // 业务代码
                String name = Thread.currentThread().getName();
                System.out.println("name = " + name);
                System.out.println("11111111");
                int a = 1 / 0;
                return null;
            });
        } catch (Exception e) {
            log.error("异步失败：", e);
            throw new GlobalException(e.getMessage());
        }

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            String message = "";
            try {
                // 业务代码
                System.out.println("11111111");
                int a = 1 / 0;
            } catch (Exception e) {
                log.error("异步失败：", e);
                message = e.getMessage();
                throw new GlobalException(e.getMessage());
            }
            return message;
        });
        future2.thenAccept(result -> {
            System.out.println("result = " + result);
            // 处理异步任务的结果
            System.out.println("2222222222222");
        });


    }

}
