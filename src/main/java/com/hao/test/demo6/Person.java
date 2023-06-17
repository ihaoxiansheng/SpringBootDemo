package com.hao.test.demo6;


import lombok.RequiredArgsConstructor;

/**
 * RequiredArgsConstructor测试
 *
 * @author xu.liang
 * @since 2023/6/9 09:06
 */

@RequiredArgsConstructor
public class Person {

    private final String name;

    private final int age;

//    @RequiredArgsConstructor
//    public Person(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }

    public static void main(String[] args) {


        Person person1 = new Person("Alice", 30); // 这个例子可以正常通过编译

//    Person person2 = new Person(); // 这个例子会报错，因为缺少必需参数 "name" 和 "age"


    }

}
