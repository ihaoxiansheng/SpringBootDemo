package com.hao.test.year.demo2024.demo2;


import java.util.Objects;
import java.util.function.Predicate;

/**
 * 花式Equals
 *
 * @author xu.liang
 * @since 2024/2/4 15:21
 */
public class testEquals {

    public static void main(String[] args) {

        String hello = "hello";
        String world = "world";
        // 1、常用的Object.equals
        System.out.println(hello.equals(world));

        // 2、避免空指针的java.util.Objects.equals
        System.out.println(Objects.equals(hello, world));

        // 3、比较数组的Objects.deepEquals
        int[] rainbowNumber = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] weekNumber = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] sameNumber = new int[]{4, 4, 4, 4};
        System.out.println(Objects.deepEquals(rainbowNumber, weekNumber));
        System.out.println(Objects.deepEquals(rainbowNumber, sameNumber));

        // 4、Junit的org.junit.Assert.assertEquals(java.lang.Object, java.lang.Object)，用于测试，如果equals结果为false则抛出Error
//        Assert.assertEquals(hello, world);

        // 5、java.util.function.Predicate花式写法
        System.out.println(Predicate.isEqual(hello).test(world));

    }

}
