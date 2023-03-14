package com.hao.springbootdemo.test.demo2;

/**
 * @author xu.liang
 * @since 2023/2/24 10:41
 */
public class DemoTest {

    public static void main(String[] args) {

        try {



            int i = 1 / 0;
        } catch (Exception e) {
            // e.printStackTrace();
            if (e.getMessage().contains("zero")) {
                throw new RuntimeException("ArithmeticException异常");

            }
        }




    }

}
