package com.hao.springbootdemo.test.demo1;

import com.alibaba.fastjson.JSONArray;

import java.util.Collections;

/**
 * @author xu.liang
 * @since 2022/3/16 20:44
 */
public class Test01 {
    public static void main(String[] args) {

        System.out.println("hello world");


        JSONArray array = new JSONArray();
        array.add("aaa");

        array.add("bbb");
        System.out.println("array = " + array);

        String str = "_copy";
        str += str;
        System.out.println(str);

        System.out.println(Collections.nCopies(3, "Apple"));


        String a = "asdasdasdasd_modelCopy";
        int index = a.indexOf("_modelCopy");
        String substring2 = a.substring(0, index);
        System.out.println("substring2 = " + substring2);
        System.out.println("index = " + index);
        String substring = a.substring(index);
        System.out.println("substring = " + substring);
        int index1 = substring.indexOf("y") + 1;
        int i = 0;
        String substring1 = substring.substring(index1);
        if ("".equals(substring1)) {
            a = a + 1;
        } else {
            i = Integer.parseInt(substring1);
            i += 1;
            a = substring2 + "_modelCopy" + i;
        }
        System.out.println("i = " + i);
        System.out.println("a = " + a);
    }

}
