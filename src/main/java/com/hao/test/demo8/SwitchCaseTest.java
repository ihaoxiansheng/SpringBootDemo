package com.hao.test.demo8;

/**
 * @author xu.liang
 * @since 2023/8/1 14:52
 */
public class SwitchCaseTest {

    public static void main(String[] args) {

        // switch case如果是只执行一个case 该case代码最后一行一定要加break或者return，不然会先匹配再按顺序执行后面的代码
        int num = 1;
        switch (num) {
            case 1:
                System.out.println("num is 1");
                break;
            case 2:
                System.out.println("num is 2");
                break;
            default:
                break;
        }


    }

}
