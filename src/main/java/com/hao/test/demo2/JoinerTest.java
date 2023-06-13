package com.hao.test.demo2;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xu.liang
 * @since 2023/2/20 16:01
 */
public class JoinerTest {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println("list = " + list);
        String join = Joiner.on(" ").join(list);
        System.out.println("join = " + join);


    }
}
