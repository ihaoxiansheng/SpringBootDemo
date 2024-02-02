package com.hao.test.year.demo2023.demo2.polymorphism;

/**
 * @author xu.liang
 * @since 2023/2/21 14:16
 */
public class Parent {
    // 父类中的成员方法
    public void method1() {
        System.out.println("父类public修饰的成员方法");
    }

    public static void main(String[] args) {

        Parent p = new Child();
        p.method1();// 子类public修饰的成员方法

    }
}

class Child extends Parent {
    // 与父类重名的成员方法
    @Override
    public void method1() {
        System.out.println("子类public修饰的成员方法");
    }
}

