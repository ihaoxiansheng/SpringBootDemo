package com.hao.pub;

import org.junit.Test;

/**
 * @author xu.liang
 * @since 2022/12/22 14:54
 */
public class Demo1222Test {

    @Test
    public void demo1() {

        try {
            int a = 1 / 0;
            System.out.println("11111");
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("22222");
            throw new RuntimeException(e);
        } finally {
            System.out.println("33333");
        }

    }


    public int test1() {
        int x = 1;
        try {
            return x;
        } finally {
            x++;
            // return x;
        }
    }

    public int test2() {
        try {
            return 1;
        } finally {
            return 2;
        }
    }

    public int test3() {
        try {
            return func1();
        } finally {
            return func2();
        }
    }
    int func1() {
        System.out.println("func1");
        return 1;
    }
    int func2() {
        System.out.println("func2");
        return 2;
    }

    /**
     * 从上面3个例子的运行结果中可以发现，try中的return语句调用的函数先于finally中调用的函数执行，也就是说return语句先执行，finally语句后执行。<br>
     * return并不是让函数马上返回，而是return语句执行后，将把返回结果放置进函数栈中，此时函数并不是马上返回，它要执行finally语句后才真正开始返回。
     */
    public static void main(String[] args) {
        Demo1222Test demo1222Test = new Demo1222Test();
        // 1
        System.out.println("demo1222Test.test1() = " + demo1222Test.test1());

        // 2
        System.out.println("demo1222Test.test2() = " + demo1222Test.test2());

        // func1 func2 2
        System.out.println("demo1222Test.test3() = " + demo1222Test.test3());
    }

}
