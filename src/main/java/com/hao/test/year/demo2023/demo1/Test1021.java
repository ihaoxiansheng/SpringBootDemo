package com.hao.test.year.demo2023.demo1;

import java.util.*;

/**
 * @author xu.liang
 * @since 2022/10/21 17:00
 */
public class Test1021 {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("aaa");

        Set<String> unique = new HashSet<>(list);
        for (String key : unique) {
            // 元素在集合里出现的次数
            System.out.println(key + ": " + Collections.frequency(list, key));
        }

    }


}
