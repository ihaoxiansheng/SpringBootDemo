package com.hao.springbootdemo.test.demo1;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author xu.liang
 * @since 2022/9/22 10:26
 */
public class Test0922 {
    public static void main(String[] args) {

        Predicate<String> predicate = list -> list.contains("好");

        List<String> stringList = Arrays.asList("好人", "好笑", "好客", "好文", "微笑");

        // List<String> filterList = StringFilter.filter(stringList, predicate);

        System.out.println("stringList = " + stringList);


        boolean test = predicate.test(stringList.toString());
        System.out.println("test = " + test);

        Gson gson = new Gson();
        String str = gson.toJson(new Object());
        String str1 = gson.toJson(new Object(){});
        System.out.println("str = " + str);
        System.out.println("str1 = " + str1);

        String[] strList = new String[]{"a", "b", "c"};
        List<String> strings = Arrays.asList(strList);
        System.out.println("strList = " + strings);

        String[] strList2 = new String[]{"a", "b", "c", "d"};
        List<String> strings2 = Arrays.asList(strList2);

        boolean b = strings2.containsAll(strings);
        System.out.println("b = " + b);


    }
}
