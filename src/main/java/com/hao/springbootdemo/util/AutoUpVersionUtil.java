package com.hao.springbootdemo.util;

import org.springframework.util.StringUtils;

/**
 * 版本自增工具类
 *
 * @author xu.liang
 * @date 2021-11-16 20:00
 **/
public class AutoUpVersionUtil {

    /**
     * 自动升级版本号，版本号+1
     *
     * @param version 版本号
     * @return string
     */
    public static String autoUpgradeVersion(String version) {
        if (StringUtils.isEmpty(version)) {
            version = "0.9.9";
        }
        // 将版本号拆解成整数数组
        String[] arr = version.split("\\.");
        int[] intArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            intArr[i] = Integer.parseInt(arr[i]);
        }
        // 递归调用
        autoUpgradeVersion(intArr, intArr.length - 1);
        // 数组转字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < intArr.length; i++) {
            stringBuilder.append(intArr[i]);
            if ((i + 1) != intArr.length) {
                stringBuilder.append(".");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 自动升级版本号，版本号+1
     *
     * @param intArr int数组
     * @param index  尾数
     */
    public static void autoUpgradeVersion(int[] intArr, int index) {
        if (index == 0) {
            intArr[0] = intArr[0] + 1;
        } else {
            int value = intArr[index] + 1;
            if (value < 10) {
                intArr[index] = value;
            } else {
                intArr[index] = 0;
                autoUpgradeVersion(intArr, index - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.err.println(autoUpgradeVersion(""));
    }

}
