package com.hao.test.year.demo2024.demo7;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xu.liang
 * @since 2024/7/8 09:35
 */
public class DemoTest {

    public static void main(String[] args) {

        // testDebug();
        int a = 50 / 50;
        System.out.println("a = " + a);
        int aa = 116 % 50;
        System.out.println("aa = " + aa);
    }

    public static void testDebug() {
        System.out.println("hello world");
        String aa = "111";
        aa = "222";
        aa = "333";
        aa = "444";

        // int a = 1 / 0;

        List<Integer> list = new ArrayList<>();
        list.add(111);
        list.add(222);
        list.add(333);
        list.add(444);
        list.add(555);
        list.add(666);
        list.add(777);
        for (int i = 0; i < list.size(); i++) {
            System.out.print("i = " + i + "\t");
            System.out.println(list.get(i));
        }
        List<String> collect = list.stream().filter(s -> s != 111).map(String::valueOf).collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }
}
