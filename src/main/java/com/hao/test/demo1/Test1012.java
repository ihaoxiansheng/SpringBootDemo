package com.hao.test.demo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Arrays.asList返回的List不支持增删操作
 *
 * @author xu.liang
 * @since 2022/10/12 11:38
 */
public class Test1012 {
    public static void main(String[] args) {

        String[] arr = {"1", "2", "3"};
        List<String> strings = new ArrayList<>(Arrays.asList(arr));
        arr[2] = "4";
        System.out.println("strings = " + strings);
        System.out.println("arr = " + Arrays.toString(arr));
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            if ("4".equals(iterator.next())) {
                iterator.remove();
            }
        }

        // ConcurrentModificationException
        strings.forEach(val -> {
            strings.remove("4");
            strings.add("3");
        });

        System.out.println("arr2 = " + Arrays.asList(arr));
    }
}
