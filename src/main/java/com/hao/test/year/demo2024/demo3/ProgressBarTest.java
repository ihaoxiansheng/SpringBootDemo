package com.hao.test.year.demo2024.demo3;

import lombok.SneakyThrows;

import java.util.stream.Stream;

/**
 * 控制台打印进度条
 *
 * @author xu.liang
 * @since 2024/3/9 16:45
 */
public class ProgressBarTest {

    @SneakyThrows
    public static void main(String[] args) {
        char incomplete = '░'; // U+2591 Unicode Character
        char complete = '█'; // U+2588 Unicode Character
        int total = 100;
        StringBuilder builder = new StringBuilder();
        // 使用 Stream 生成器生成了包含100个未完成字符的流，并通过 forEach 方法将这些字符添加到 builder 中。
        Stream.generate(() -> incomplete).limit(total).forEach(builder::append);
        for (int i = 0; i < total; i++) {
            // 使用 builder.replace(i, i + 1, String.valueOf(complete)) 方法将第 i 个未完成字符替换为已完成字符，从而更新进度条。
            builder.replace(i, i + 1, String.valueOf(complete));
            String progressBar = "\r" + builder;
            String percent = " " + (i + 1) + "%";
            // █████████████████████████████████████████████████████████████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░ 61%
            System.out.print(progressBar + percent);
            Thread.sleep(i * 5L);
        }
    }

}
