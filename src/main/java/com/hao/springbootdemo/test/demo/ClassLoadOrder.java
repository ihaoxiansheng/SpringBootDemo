package com.hao.springbootdemo.test.demo;

/**
 * Java中类加载执行顺序是：
 * 主类中的静态代码块–>父类中的静态成员和静态代码块–>子类中的静态成员和静态代码块–>父类中的成员变量和构造代码块–>父类中的构造方法–>子类中的成员变量和构造代码块–>子类构造方法
 * <p>
 * // 执行顺序
 * // 主类中的静态代码块
 * // 父类中的静态属性
 * // 父类的静态代码块
 * // 子类中的静态属性
 * // 子类中的静态代码块
 * // 父类中的普通属性
 * // 父类的构造代码块
 * // 父类的无参构造方法
 * // 子类中的普通属性
 * // 子类中的构造代码块
 * // 子类中的无参构造方法
 * // ---------------
 * // 父类中的普通属性
 * // 父类的构造代码块
 * // 父类的无参构造方法
 * // 子类中的普通属性
 * // 子类中的构造代码块
 * // 子类中的有参构造方法5
 * // main方法中的函数1
 * // main方法中的普通代码块1
 * // main方法中的函数2
 * // main方法中的普通代码块2
 * <p>
 * 静态代码块只会在类加载的时候执行一次。构造代码块其实应该称之为实例代码块，每次在构建类实例的时候执行。
 *
 * @author xu.liang
 * @since 2022/9/24 14:27
 */
class Father {
    /**
     * 静态属性
     */
    public static String str1 = "父类中的静态属性";

    /**
     * 普通属性
     */
    public String str2 = "父类中的普通属性";

    /**
     * 无参构造方法
     */
    public Father() {
        System.out.println("父类的无参构造方法");
    }

    /**
     * 有参构造方法
     */
    public Father(int a) {
        System.err.println("父类的有参构造方法" + a);
    }

    // 静态代码块
    static {
        System.out.println(str1);
        System.out.println("父类的静态代码块");
    }

    // 构造代码块
    {
        System.out.println(str2);
        System.out.println("父类的构造代码块");
    }
}

class Son extends Father {
    // 子类中的静态属性
    public static String str1 = "子类中的静态属性";
    // 子类中的普通属性
    public String str2 = "子类中的普通属性";

    /**
     * 子类中的无参构造方法
     */
    public Son() {
        System.out.println("子类中的无参构造方法");
    }

    /**
     * 子类中的有参构造方法
     */
    public Son(int a) {
        //super(a);
        System.out.println("子类中的有参构造方法" + a);
    }

    // 子类中的静态代码块
    static {
        System.out.println(str1);
        System.out.println("子类中的静态代码块");
    }

    // 子类中的构造代码块
    {
        System.out.println(str2);
        System.out.println("子类中的构造代码块");
    }
}

class TestExecute {
    public static void main(String[] args) {
        Son s = new Son();
        System.out.println("---------------");
        Son a = new Son(5);
        System.out.println("main方法中的函数1");
        {
            System.out.println("main方法中的普通代码块1");
        }
        System.out.println("main方法中的函数2");
        {
            System.out.println("main方法中的普通代码块2");
        }
    }

    static {
        System.out.println("主类中的静态代码块");
    }

}

