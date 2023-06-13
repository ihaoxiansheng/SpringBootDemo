package com.hao.pub;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试continue、return、break
 *
 * @author xu.liang
 * @since 2022/12/16 17:44
 */
public class Demo1216Test {

    @Test
    public void test() {
        List<String> list = new ArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");

        for (String s : list) {
            System.out.println("s = " + s);

            if ("2".equals(s)) {
                try {
                    int a = 1 / 0;
                } catch (Exception e) {
                    continue;
                    // return;
                    // break;
                }
            }
            System.out.println("后续代码");

        }


    }


}
