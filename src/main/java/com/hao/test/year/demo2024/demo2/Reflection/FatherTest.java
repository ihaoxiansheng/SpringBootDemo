package com.hao.test.year.demo2024.demo2.Reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author xu.liang
 * @since 2024/2/22 11:40
 */
public class FatherTest {
    public void addTest(Object... objects) {
        System.out.println(objects.length);
    }

    public int add(int[] a, int[] b) {
        return 0;
    }

    public int addProxy(Object[] objects) {
        return add((int[]) objects[0], (int[]) objects[1]);
    }

    public static void main(String[] args) {
        int[] a = {0, 1};
        int[] b = {1, 2};
        Object[] objects = {a, b};
        FatherTest son = new FatherTest();
        try {
            Method m = FatherTest.class.getDeclaredMethod("addTest", Object[].class);
            Method m2 = FatherTest.class.getDeclaredMethod("add", int[].class, int[].class);
            Method m3 = FatherTest.class.getDeclaredMethod("addProxy", Object[].class);
            System.out.println("参数: m " + m.getParameterCount()); // -->结果为1
            System.out.println("参数: m2 " + m2.getParameterCount()); // -->结果为2
            System.out.println("参数: m3 " + m3.getParameterCount()); // -->结果为1
            m.invoke(son, (Object) objects); // 输入的参数个数为1个
            m2.invoke(son, objects); // 输入的参数个数为2个
            m3.invoke(son, (Object) objects); // 输入的参数个数为1个

            System.out.println("=============================");
            son.addTest(a, b); // -->结果为2
            son.addTest(objects); // -->结果为2
            son.addTest(a); // -->结果为1
            son.addTest(new Object[2]); // -->结果为2
            son.addTest(new int[2]); // -->结果为1
            son.addTest(new String[2]); // -->结果为2
            son.addTest((Object)objects); // -->结果为1
            son.addTest((Object) new String[2]); // -->结果为1
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
