package com.hao.springbootdemo.test.demo1;

/**
 * @author xu.liang
 * @since 2022/12/8 15:56
 */
public class Test1208 {

    public static <T> T strToOther(String v1, int v2) {
        if (v2 == 0) {
            Integer integer = Integer.valueOf(v1);
            return (T) integer;
        } else if (v2 == 1) {
            Long aLong = Long.valueOf(v1);
            return (T) aLong;
        } else if (v2 == 2) {
            Float aFloat = Float.valueOf(v1);
            return (T) aFloat;
        } else if (v2 == 3) {
            Double aDouble = Double.valueOf(v1);
            return (T) aDouble;
        }
        return (T) v1;
    }

    public static void main(String[] args) {
        // Object o = strToOther("111", 3);
        //
        // int a =  strToOther("111", 0);
        // inta(strToOther("111", 3));
        //
        // System.out.println("" + (o instanceof Integer));


        Object o1 = strToOther("111", 1);

        Long a1 = strToOther("111", 1);
        longa(strToOther("111", 1));

        System.out.println("" + (o1 instanceof Long));
    }

    public static void inta(int v1) {
        System.out.println("v1 = " + v1);
    }

    public static void longa(long v1) {
        System.out.println("v1 = " + v1);
    }
}
