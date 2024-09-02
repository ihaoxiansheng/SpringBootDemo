package com.hao.test.year.demo2024.demo4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorizeString {
    public static void main(String[] args) {
        // 原始字符串
        // String input = "这是一段包含关键词的文本，关键词将被着色显示。关键词可以是任何内容，例如：关键词1、关键词2、关键词3。";
        String input = "这是一段包含关键词的文本，【是否重保】关键词将被着色显示。关键词可以是任何内容，例如：关键词1、关键词2、关键词3。";

        // 匹配的关键词
        String keyword = "【是否重保】";

        // 匹配的颜色
        // String color = "\u001B[31m"; // 红色
        String color = "\u001B[34m"; // 蓝色

        // 构建正则表达式
        String regex = "(" + Pattern.quote(keyword) + ")";

        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);

        // 匹配器
        Matcher matcher = pattern.matcher(input);

        // 替换匹配内容为指定颜色
        String coloredOutput = matcher.replaceAll(color + "$1" + "\u001B[0m");

        // 输出结果
        System.out.println("原始字符串：" + input);
        System.out.println("着色后字符串：" + coloredOutput);
    }
}
