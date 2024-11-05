package com.hao.test.year.demo2024.demo9;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @author xu.liang
 * @since 2024/9/11 11:15
 */
public class DemoTest2 {
    private static final Pattern REGEX_SCENE_VERSION = Pattern.compile("^[1-9]\\d*\\.[0-9]$");
    public static void main(String[] args) {

        String version1 = "1.0";
        String version2 = "1.1";
        String version3 = "1.4";
        String version4 = "2.3";
        String version5 = "2.5";
        String version6 = "1.0.0";
        String versionNow = "1.0";
        int i = versionNow.compareTo(version6);
        System.out.println("i = " + i);
        System.out.println(i < 0 ? "version1 < version2" : "version1 > version2");

        System.out.println("DateUtil.now() = " + DateUtil.now());

        if (!REGEX_SCENE_VERSION.matcher("123132").matches()) {
            System.out.println("======================");
        }

        String str1 = "\"123\"";
        String str2 =  "'123'";
        String str3 =  "\"'123'\"";
        String strip1 = StringUtils.strip(str1, "\"");
        System.out.println("strip1 = " + strip1);
        String strip2 = StringUtils.strip(str2, "'");
        System.out.println("strip2 = " + strip2);

        String strip3 = StringUtils.strip(StringUtils.strip(str3, "\""), "'");
        System.out.println("strip3 = " + strip3);

    }
}
