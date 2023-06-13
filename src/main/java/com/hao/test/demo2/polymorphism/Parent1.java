package com.hao.test.demo2.polymorphism;

/**
 * @author xu.liang
 * @since 2023/2/21 14:18
 */
public class Parent1 {
    // 成员变量
    public String publicString = "父类public修饰的成员变量";

    // 静态变量
    public static String staticString = "父类static修饰的静态变量";

    // 静态方法
    public static void method2() {
        System.out.println("父类public static修饰的成员方法");
    }

    public static void main(String[] args) {
        Parent1 p = new Child1();
        System.out.println(p.publicString); // 父类public修饰的成员变量
        System.out.println(p.staticString);// 父类static修饰的静态变量
        p.method2();// 父类public static修饰的成员方法
        System.out.println(" ========================================== ");
        Child1 c1 = new Child1();
        System.out.println(c1.publicString);
        System.out.println(c1.staticString);
        c1.method2();

    }

}

class Child1 extends Parent1 {
    //与父类同名的成员变量
    public String publicString = "子类public修饰的成员变量";

    //与父类同名的静态变量
    public static String staticString = "子类static修饰的静态变量";

    // 与父类重名的静态方法
    public static void method2() {
        System.out.println("子类public static修饰的成员方法");
    }

}

