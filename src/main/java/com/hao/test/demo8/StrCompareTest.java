package com.hao.test.demo8;

import cn.hutool.core.util.HashUtil;

/**
 * 字符串比较是否相等
 *
 * @author xu.liang
 * @since 2023/8/4 10:02
 */
public class StrCompareTest {


    public static void main(String[] args) {

        String str1 = "A,B,C,D";
        String str11 = "A,B,C,D";
        String str2 = "A,C,B,D";
        int i1 = HashUtil.oneByOneHash(str1);
        System.out.println("i1  = " + i1);
        int i11 = HashUtil.oneByOneHash(str11);
        System.out.println("i11 = " + i11);
        int i2 = HashUtil.oneByOneHash(str2);
        System.out.println("i2  = " + i2);


    }

}
