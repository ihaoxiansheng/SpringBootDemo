package com.hao.test.year.demo2023.demo1;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xu.liang
 * @since 2022/12/10 10:20
 */
public class Test1210 {

    public static void main(String[] args) {

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();

        list1.add("1");
        list1.add("2");

        list2.add("1");
        list2.add("2");

        list3.add("2");
        list3.add("1");

        // true
        System.out.println(ListUtils.isEqualList(list1, list2));
        // false
        System.out.println(ListUtils.isEqualList(list1, list3));
        // true
        System.out.println(CollectionUtil.isEqualList(list1, list2));
        // false
        System.out.println(CollectionUtil.isEqualList(list1, list3));

    }
}
