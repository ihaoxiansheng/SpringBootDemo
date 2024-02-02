package com.hao.test.year.demo2023.demo4;

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

        Result result = new Result("12312311", "21231231");
        String createTable = result.createTable;
        String mapStr = result.mapStr;
        System.out.println("createTable = " + createTable);
        System.out.println("mapStr = " + mapStr);
        String substring = result.createTable.substring(0, 3);
        System.out.println("substring = " + substring);
        String a = "123";
        // Cannot assign a value to final variable 'createTable'
        result.createTable = a;
        System.out.println("result.createTable = " + result.createTable);



    }


    private static class Result {
        public String createTable;
        public final String mapStr;

        public Result(String createTable, String mapStr) {
            this.createTable = createTable;
            this.mapStr = mapStr;
        }
    }
}
