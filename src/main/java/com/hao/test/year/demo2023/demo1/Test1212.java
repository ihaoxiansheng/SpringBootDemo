package com.hao.test.year.demo2023.demo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * System.arraycopy
 *
 * @author xu.liang
 * @since 2022/12/12 17:55
 */
public class Test1212 {

    public static List<String> arrayAdds1(List<String> v1, List<String> v2) {
        String join = String.join(",", v2);
        String[] arr2 = {join};
        System.out.println("arr = " + Arrays.toString(arr2));
        String[] v1str = new String[arr2.length];
        try {
            System.arraycopy(arr2, 0, v1str, 0, arr2.length);
            v1 = Arrays.asList(v1str);
            System.out.println("v1 = " + v1);
        } catch (Exception e) {
            // transfer exception:java.lang.ArrayStoreException
            System.out.println("transfer exception:" + e);
        }
        // System.arraycopy(v1.toArray(), 0, v1.toArray(), 3, 2);
        return v1;
    }

    public static void main(String[] args) {
        List<String> v1 = new ArrayList<>();
        List<String> v2 = new ArrayList<>();
        v2.add("1");
        v2.add("2");
        v2.add("3");
        v2.add("4");
        v2.add("5");
        // List<String> strings = arrayAdds1(v1, v2);
        List<String> v11 = arrayAdds1(v1, v2);
        System.out.println("v11 = " + v11);

        // copySelf();
        // System.out.println("===============================");
        // copyToOther();
        // System.out.println("===============================");
        // getIntegerArrayFromObjectArray();
        // System.out.println("===============================");
        // getStringArrayFromObjectArray1();
        // System.out.println("===============================");
        // getStringArrayFromObjectArray2();
        // System.out.println("===============================");

    }


    private static void copySelf() {
        int[] ids = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(ids));// [1, 2, 3, 4, 5]
        // System.arraycopy(src, srcPos, dest, destPos, length);
        // 把从索引0开始的2个数字复制到索引为3的位置上
        System.arraycopy(ids, 0, ids, 3, 2);
        System.out.println(Arrays.toString(ids));// [1, 2, 3, 1, 2]
    }

    private static void copyToOther() {
        int[] ids = {1, 2, 3, 4, 5};
        // 将数据的索引1开始的3个数据复制到目标的索引为0的位置上
        int[] other = new int[5];
        System.arraycopy(ids, 1, other, 0, 3);
        System.out.println(Arrays.toString(ids));// [1, 2, 3, 4, 5]深复制
        System.out.println(Arrays.toString(other));// [2, 3, 4, 0, 0]
    }

    /**
     * 如果是类型转换问题，获取整形
     */
    private static void getIntegerArrayFromObjectArray() {
        Object[] obj1 = {1, 2, 3, "4", "5"};
        Integer[] obj2 = new Integer[5];

        try {
            System.arraycopy(obj1, 0, obj2, 0, obj1.length);
        } catch (Exception e) {
            System.out.println("transfer exception:" + e);
        }
        System.out.println(Arrays.toString(obj1));// [1, 2, 3, 4, 5]
        System.out.println(Arrays.toString(obj2));// [1, 2, 3, null, null]
    }

    /**
     * 获取Object数组中的字符串类型数据
     */
    private static void getStringArrayFromObjectArray1() {
        Object[] obj3 = {1, 2, 3, "4", "5"};
        String[] obj4 = new String[5];
        try {
            System.arraycopy(obj3, 2, obj4, 2, 3);
        } catch (Exception e) {
            // transfer exception:java.lang.ArrayStoreException
            System.out.println("transfer exception:" + e);
        }

        System.out.println(Arrays.toString(obj3));// [1, 2, 3, 4, 5]

        System.out.println(Arrays.toString(obj4));// [null, null, null, null, null]
    }

    /**
     * 获取Object数组中的字符串类型数据
     */
    private static void getStringArrayFromObjectArray2() {
        Object[] obj3 = {1, 2, 3, "4", "5"};
        String[] obj4 = new String[5];
        try {
            System.arraycopy(obj3, 3, obj4, 3, 2);
        } catch (Exception e) {
            System.out.println("transfer exception:" + e);
        }
        System.out.println(Arrays.toString(obj3));// [1, 2, 3, 4, 5]
        System.out.println(Arrays.toString(obj4));// [null, null, null, 4, 5]
        obj3[3] = "ZhangSan";
        System.out.println("查看是浅复制还是深复制~~~~~");
        System.out.println(Arrays.toString(obj3));// [1, 2, 3, ZhangSan, 5]
        System.out.println(Arrays.toString(obj4));// [null, null, null, 4, 5]
    }

}
