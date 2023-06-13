package com.hao.test.demo2;

import com.hao.entity.User;

/**
 * JAVA方法中的参数用final来修饰防止数据在方法体中被修改
 *
 * @author xu.liang
 * @since 2023/2/16 11:05
 */
public class MethodParamAddFinalTest {

    public static void main(String[] args) {

        int i = 1;
        System.out.println(i);
        checkInt1(i);
        System.out.println(i);

        User user = new User();
        user.setName("语文");
        user.setAge("33");
        System.out.println("user = " + user);
        checkInt2(user);
        System.out.println("user = " + user);

    }

    /**
     * 对于基本类型，基本类型的值在方法内部是不能够改变的
     */
    public static void checkInt1(final int i) {

        // 注释打开 编译不通过，final修饰的局部变量i的值是不能够改变的（个人理解相当于最终确定的值，不能再被赋值了）
        // i = 10;

    }

    /**
     * 对于引用类型
     */
    public static void checkInt2(final User user) {
        // user变量的引用是不能够改变的，否则的话，编译会报错
        // user = new User();
        // user变量的值是能够修改的，但所指向的引用是不能够改变的
        user.setName("数学");

    }

}
