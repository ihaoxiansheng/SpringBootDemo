package com.hao.test.year.demo2024.demo4;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xu.liang
 * @since 2024/4/23 19:10
 */
@Slf4j
public class Demo2Test {

    public static void main(String[] args) {

        String optionCN = switchTest("==");
        System.out.println("optionCN = " + optionCN);


    }

    public static String switchTest(String option) {

        String optionCN = "";
        switch (option) {
            case "==":
                optionCN = "等于";
                break;
            case ">":
                optionCN = "大于";
                break;
            case "<":
                optionCN = "小于";
                break;
            case "!=":
                optionCN = "不等于";
                break;
            case ">=":
                optionCN = "大于等于";
                break;
            case "<=":
                optionCN = "小于等于";
                break;
            case "exist":
                optionCN = "存在";
                break;
            case "notExist":
                optionCN = "不存在";
                break;
            case "在...中":
                optionCN = "在...中";
                break;
            case "不在...中":
                optionCN = "不在...中";
                break;
            default:
                log.info("条件option入参为：{}", option);
                break;
        }
        return optionCN;
    }

}
