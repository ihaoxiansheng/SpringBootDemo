package com.hao.test.year.demo2024.demo2.Reflection;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author xu.liang
 * @since 2024/2/22 11:26
 */
public class ArrayUtilTest {

    @SneakyThrows
    public static void main(String[] args) {

        Class<?> clazz = ArrayUtilTest.class;
        // 获取 Method 对象
        Method method = clazz.getMethod("print");

        // 获取工具类的print()方法
        ArrayUtilTest arrayUtilTest = new ArrayUtilTest();
        Method method1 = ArrayUtilTest.class.getDeclaredMethod("print");
        // 通过反射调用方法，method或method都可
        method.invoke(arrayUtilTest);
//        method1.invoke(arrayUtilTest);
    }

    public void print() {
        String[] arr = {"点赞", "关注", "评论", "一键三连"};
        System.out.println(Arrays.toString(arr));
    }

}
