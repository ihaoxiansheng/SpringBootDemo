package com.hao.test.year.demo2024.demo3;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 测试并行流ParallelStream
 * 简单任务推荐使用串行流。
 * 复杂任务推荐使用并行流。
 * 简单任务可以理解为要执行的任务占用CPU时间小于1ms，反之复杂任务就是大于1ms。<p>
 * 在使用stream.foreach时这个遍历没有线程安全问题，
 * 但是使用parallelStream就会有线程安全问题，所有在parallelStream里面使用的外部变量，
 * 比如集合一定要使用线程安全集合，不然就会引发多线程安全问题。
 *
 * @author xu.liang
 * @since 2024/3/21 19:37
 */
public class TestParallelStream {

    private static final long DATA_COUNT = 10000;

    public static void main(String[] args) {
        testConcurrency();

        test1();

        List<Double> list = new ArrayList<>();
        for (int i = 0; i < DATA_COUNT; i++) {
            list.add(Math.random() * 1000 % 500);
        }

        long ctime = test2(list);
        long stime = test3(list);
        long mtime = test4(list);

        System.out.println("for 循环单线程查找，共用时(ms)：" + (ctime));
        System.out.println("串行流单线程查找，共用时(ms)：" + (stime));
        System.out.println("并行流多线程查找，共用时(ms)：" + (mtime));
    }

    private static void testConcurrency() {
        for (int i = 0; i < 1000; i++) {
            // 1、CopyOnWriteArrayList线程安全
//            List<String> trueList1 = new CopyOnWriteArrayList<>();
            // 2、Collections.synchronizedList()
//            List<String> demoList = new ArrayList<>();
//            List<String> trueList2 = Collections.synchronizedList(demoList);
            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            list1.add("a");
            list1.add("b");
            list1.add("c");
            list1.add("d");
            list1.parallelStream().forEach(list -> list2.add(list));
            // 在高并发下会出现size为3的情况，原因：ArrayList是线程不安全的，在并行流时，会出现并发问题
            // 线程不安全的情况在于多线程的情况下修改共享变量
            System.out.println(list2.size());
        }
    }

    private static void test1() {
        List<String> list = Arrays.asList("apple", "banana", "orange", "grape", "peach");

        // 串行流操作（单线程）
        list.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("---------");

        // 并行流操作（多线程）
        list.parallelStream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    private static long test2(List<Double> list) {

        long startTime = System.currentTimeMillis();

        List<Double> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            // 模拟复杂任务处理（如访问数据库、读写文件、访问网络等）
            sleep(1);
            Double val = list.get(i);
            if (val > 250 && val < 251) {
                result.add(val);
            }
        }

        for (Double val : result) {
            System.out.println(val);
        }
        ;
        long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }


    private static long test3(List<Double> list) {

        long startTime = System.currentTimeMillis();

        Stream<Double> result = list.stream().filter((val) -> {
            // 模拟复杂任务处理（如访问数据库、读写文件、访问网络等）
            sleep(1);
            return val > 250 && val < 251;
        });


        result.forEach(val -> {
            System.out.println(val);
        });
        long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    private static long test4(List<Double> list) {

        long startTime = System.currentTimeMillis();

        Stream<Double> result = list.parallelStream().filter((val) -> {
            // 模拟复杂任务处理（如访问数据库、读写文件、访问网络等）
            sleep(1);
            return val > 250 && val < 251;
        });


        result.forEach(val -> {
            System.out.println(val);
        });
        long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }


    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

