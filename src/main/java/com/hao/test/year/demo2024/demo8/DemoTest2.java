package com.hao.test.year.demo2024.demo8;

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xu.liang
 * @since 2024/8/30 16:48
 */
public class DemoTest2 {

    public static void main(String[] args) {
        int columnIndex = -1;
        List<String> ret = new ArrayList<>();
        List<String> test = new ArrayList<>();
        test.add("客户名称");
        test.add("客户名称11");
        test.add("客户名称22");
        List<List<String>> aaa = new ArrayList<>();
        aaa.add(test);
        aaa.add(test);
        for (int i = 0; i < aaa.size(); i++) {
            List<String> row = aaa.get(i);
            if (row != null) {
                for (int j = 0; j <= row.size(); j++) {
                    if ("客户名称".equals(row.get(j))) {
                        columnIndex = j;
                        break;
                    }
                }
                if (ObjectUtils.isNotEmpty(columnIndex)) {
                    continue;
                }
                // 装载obj
                ret.add("1");
            } else {
                ret.add("2");
            }
        }
        System.out.println("columnIndex = " + columnIndex);
        System.out.println("ret = " + ret);
    }
}
