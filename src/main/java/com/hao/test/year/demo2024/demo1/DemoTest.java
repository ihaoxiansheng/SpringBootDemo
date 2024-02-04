package com.hao.test.year.demo2024.demo1;

import java.util.Objects;

/**
 * @author xu.liang
 * @since 2024/1/10 09:45
 */
public class DemoTest {

    public static void main(String[] args) {

        String t1 = null;
        String t2 = "null";
        // 对象的 equals 方法容易抛空指针异常，应使用常量或确定有值的对象来调用 equals 方法。限流
        // 当然，使用 java.util.Objects.equals() 方法是最佳实践。
        System.out.println("Objects.equals(t1, t2) = " + Objects.equals(t1, t2));
//        System.out.println("t1.equals(t2) = " + t1.equals(t2));


    }


}
