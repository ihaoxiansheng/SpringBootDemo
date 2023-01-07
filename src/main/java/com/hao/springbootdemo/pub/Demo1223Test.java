package com.hao.springbootdemo.pub;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 给你一个无穷字符串类型的IP，IP之间以，分割，类似这样String ipStr = "192.168.10.222,192.168.10.43,192.168.10.243"，
 * 写一个方法，入参为 ipStr，出参为 ipStr的最后一个 Ip
 *
 * @author xu.liang
 * @since 2022/12/23 17:41
 */
public class Demo1223Test {
    /**
     * 计时器
     */
    private static StopWatch stopWatch = new StopWatch();

    public static void main(String[] args) {
        StringBuilder str = new StringBuilder();
        int n = 1000000;
        for (int i = 1; i <= n; i++) {
            str.append(i).append(".")
                    .append(i).append(".")
                    .append(i).append(".")
                    .append(i);
            if (i != n) {
                str.append(",");
            }
        }
        // System.out.println("str = " + str);
        stopWatch.start();
        String s = ipStr(String.valueOf(str));
        stopWatch.stop();
        // 1s = 10亿ns
        System.out.println("运算耗时为：= " + stopWatch.prettyPrint());
        System.out.println("计算值为：= " + s);
    }

    public static String ipStr(String str) {
        // 1
        // String[] ipList = str.split(",");
        // ArrayList<String> arrayList = new ArrayList<>();
        // for (String s : ipList) {
        //     arrayList.add(s);
        // }
        // // 或Collections.reverse(list)；list.get(0);
        // // 或str.substring(str.lastIndexOf(,) + 1);
        // str = arrayList.get(arrayList.size() - 1);
        // return str;

        // 2
        int i = str.length();
        System.out.println("i = " + i);
        while (str.charAt(i - 1) != ',') {
            i--;
        }
        System.out.println("i1 = " + i);
        return str.substring(i);
    }
}
