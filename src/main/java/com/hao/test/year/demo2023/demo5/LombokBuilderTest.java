package com.hao.test.year.demo2023.demo5;

import com.hao.entity.User1;

/**
 * Builder(toBuilder=true)
 * class Foo {
 * int x;
 * ...
 * }
 * Foo f0 = Foo.builder().build();
 * Foo f1 = f0.toBuilder().x(42).build();
 * 如果Foo有父类继承(extend) 需要使用@SuperBuilder(toBuilder = true)，同时父类也要有@SuperBuilder(toBuilder = true)，不然会报错误，比如符号错误
 * <p>浅拷贝
 *
 * @author xu.liang
 * @since 2023/5/4 15:00
 */
public class LombokBuilderTest {

    public static void main(String[] args) {

        User1 user1 = User1.builder().build();
        user1.setName("John");

        User1 john1 = user1.toBuilder().name("John1").build();
        System.out.println("john1 = " + john1); // John1

    }


}
