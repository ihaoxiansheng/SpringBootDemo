package com.hao.test.year.demo2024.demo2.Reflection;

import java.lang.reflect.Method;

/**
 * Reflection反射调用类中的方法测试
 *
 * @author xu.liang
 * @since 2024/2/21 10:49
 */
public class ReflectionTest {

    public static void main(String[] args) throws Exception {
        // 获取 Class 对象
        Class<?> clazz = MyClassTest.class;

        // 获取 Method 对象
        Method method = clazz.getMethod("myMethod", String.class);

        // 创建类实例（如果方法是非静态的）
        MyClassTest obj = new MyClassTest();

        // 设置访问权限
        method.setAccessible(true);

        // 调用方法
        method.invoke(obj, "Hello, Reflection!");
    }
}

class MyClassTest {
    public void myMethod(String message) {
        System.out.println("Message: " + message);
    }
}
