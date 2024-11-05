package com.hao.test.year.demo2024.demo9;

public class Main {
    public static void main(String[] args) {
        // 示例字符串
        String aaa = "aaa->消费处->中国极限云网运营部->中国极限";

        // 目标字符串
        String target = "中国极限云网运营部";

        // 获取目标字符串在整体中的位置
        int targetIndex = aaa.indexOf(target);
        System.out.println("targetIndex = " + targetIndex);

        // 如果目标字符串存在
        if (targetIndex != -1) {
            // 找到目标字符串前面的最后一个 "->" 位置
            int lastArrowIndex = aaa.lastIndexOf("->", targetIndex);
            System.out.println("lastArrowIndex = " + lastArrowIndex);

            // 如果找到了 "->"，则提取前一级
            if (lastArrowIndex != -1) {
                // 提取前一级，去掉箭头并修剪空格
                String previousLevel = aaa.substring(0, lastArrowIndex);
                System.out.println("previousLevel = " + previousLevel);
                if (previousLevel.contains("->")) {
                    previousLevel = previousLevel.substring(previousLevel.lastIndexOf("->") + 2);
                }

                // 输出结果
                System.out.println("前一级为: " + previousLevel);
            } else {
                System.out.println("未找到前一级");
            }
        } else {
            System.out.println("未找到目标字符串");
        }
    }
}
