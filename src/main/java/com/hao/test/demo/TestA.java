package com.hao.test.demo;

/**
 * 类加载的顺序如上分析，在main方法中的普通方法和普通代码块按代码先后顺序执行，此时和类加载没什么关系。
 * 在同一个类中静态属性和静态代码块的加载顺序取决于他们在类中出现的先后顺序，并且不能调用未定义的属性，即定义在前，调用在后
 * <p>
 * 执行顺序
 * // Test--A
 * // 静态初始代码块
 * // Test--B
 *
 * @author xu.liang
 * @since 2022/9/24 14:31
 */
public class TestA {
    public TestA() {
        System.out.println("Test--A");
    }
}

class TestB {
    public TestB() {
        System.out.println("Test--B");
    }
}

class TestExecute1 {
    /**
     * 静态属性
     */
    public static TestA a = new TestA();

    // 静态代码块
    static {
        System.out.println("静态初始代码块");
        // 不能调用未定义的属性，此处会报错
        // System.out.println(b);
    }

    /**
     * 静态属性
     */
    public static TestB b = new TestB();

    public static void main(String[] args) {
        new TestExecute1();
    }

}

