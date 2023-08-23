package com.hao.test.demo8;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xu.liang
 * @since 2023/8/14 15:05
 */
public class DemoTest {

    public static void main(String[] args) {

        List<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        System.out.println("arrayList1 = " + arrayList);
        List<String> strings2 = addList(arrayList);
        System.out.println("arrayList2 = " + arrayList);

    }

    // 自己写个简单的测试，main方法里面new一个list，然后传给另一个方法，在另一个方法里面去add一些元素然后在main方法看看list有没有变
    public static List<String> addList(List<String> a) {
        // arrayList2 = [1, 2, 3, 4]
        List<String> list = a;
        // 此时arrayList2 = arrayList1
//        List<String> list = new ArrayList<>(a);
        System.out.println("list = " + list);
        list.add("4");
        return list;
    }


}
