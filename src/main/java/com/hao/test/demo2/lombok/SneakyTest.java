package com.hao.test.demo2.lombok;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * lombok测试注解@SneakyThrows，编译时自动try catch（查看编译后的class文件可知）@SneakyThrows(xxx.class) 可指定需要try catch的异常xxx
 *
 * @author xu.liang
 * @since 2023/2/7 16:55
 */
public class SneakyTest {

    public static void main(String[] args) {
        exceptionTest();
        // exceptionTest1();
    }

    @SneakyThrows(FileNotFoundException.class)
    public static void exceptionTest() {

        // 模拟一个异常
        new FileInputStream("D:\\test.txt");

        int a = 1 / 0;
        System.out.println(a);
    }

    // public static void exceptionTest1() {
    //
    //     // 模拟一个异常
    //     try {
    //         new FileInputStream("D:\\test.txt");
    //     } catch (FileNotFoundException e) {
    //         e.printStackTrace();
    //     }
    //
    // }

}
