package com.hao.test.year.demo2024.demo2;

import java.text.Collator;
import java.util.Arrays;

/**
 * 中文姓名排序
 *
 * @author xu.liang
 * @since 2024/2/19 15:29
 */
public class NameSort {

    public static void main(String[] args) {

        String[] names = {"张三", "李四", "王五", "赵六", "哈喽"};
        // Collator是一个抽象基类，子类实现特定的整理策略
        Arrays.sort(names, Collator.getInstance(java.util.Locale.CHINA));
        for (String name : names) {
            System.out.println(name);
        }

    }

}
