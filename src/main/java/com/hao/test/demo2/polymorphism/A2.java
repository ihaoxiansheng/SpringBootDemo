package com.hao.test.demo2.polymorphism;

import com.google.gson.Gson;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 只有父类对象本身就是用子类new出来的时候，才可以在将来被强制转换为子类对象。
 *
 * @author xu.liang
 * @since 2023/2/7 16:05
 */
@Data
public class A2 extends A1 {

    private String name;

    private static void test(A1 a1) {
        A2 a2 = (A2) a1;
        System.out.println("a2 = " + a2);
        Map<Object, Object> map = new HashMap<>();

        map.put("a", a1.getName());
        map.put("b", a1.getName());
        map.put("c", a1.getName());
        Gson gson = new Gson();
        String str = gson.toJson(map);
        System.out.println("str = " + str);


        try {
            int i = 1 / 0;
            System.out.println("执行了");
        } catch (Exception e) {
            // e.printStackTrace();
            // if (e.getMessage().contains("zero")) {
            //     throw new RuntimeException("ArithmeticException异常");
            //
            // }
        }

    }

    public static void main(String[] args) {
        // 执行失败 ClassCastException
        // A1 a11 = new A1();
        // test(a11);


        // 执行通过 因为父类a1是用子类new出来的，实际上还是子类 --->多态
        A1 a1 = new A2();
        a1.setName("haha");
        test(a1);


    }

}

