package com.hao.test.demo4;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 压缩和解压缩字符串
 *
 * @author xu.liang
 * @since 2023/4/18 21:29
 */
@Slf4j
public class compress {

    public static void main(String[] args) {
        String str = "hello world";
        double size = (double) str.getBytes().length / (1024 * 1024);
        log.info("字符串大小：" + size + " MB");

        String compressedStr = compress(str);
        System.out.println("原始字符串长度：" + str.length());
        System.out.println("压缩后字符串长度：" + compressedStr.length());
        System.out.println("压缩后字符串：" + compressedStr);
        double compressedStrSize = (double) compressedStr.getBytes().length / (1024 * 1024);
        log.info("compressedStr字符串大小：" + compressedStrSize + " MB");

        if (JudgeBase64String(str)) {
            String decompressStr = decompress(str);
            double size2 = (double) decompressStr.getBytes().length / (1024 * 1024);
            log.info("decompressStr字符串大小：" + size2 + " MB");
        }

    }

    public static boolean JudgeBase64String(String str) {
        try {
            String decodedStr = new String(Base64.getDecoder().decode(str), StandardCharsets.UTF_8);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @SneakyThrows
    public static String compress(String str) {
        // 将字符串转换为字节数组
        byte[] data = str.getBytes(StandardCharsets.UTF_8);

        // 创建一个ByteArrayOutputStream对象
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // 创建一个GZIPOutputStream对象
        GZIPOutputStream gzip = new GZIPOutputStream(out);

        // 将字节数组写入GZIPOutputStream对象
        gzip.write(data);

        // 关闭GZIPOutputStream对象
        gzip.close();

        // 获取ByteArrayOutputStream对象中的压缩后的字节数组
        byte[] compressedData = out.toByteArray();

        // 使用Base64编码将压缩后的字节数组转换为字符串
        return Base64.getEncoder().encodeToString(compressedData);
    }

    @SneakyThrows
    public static String decompress(String compressedStr) {
        // 将压缩后的字符串使用Base64解码成字节数组
        byte[] compressedData = Base64.getDecoder().decode(compressedStr);

        // 创建一个ByteArrayInputStream对象
        ByteArrayInputStream in = new ByteArrayInputStream(compressedData);

        // 创建一个GZIPInputStream对象
        GZIPInputStream gzip = new GZIPInputStream(in);

        // 创建一个ByteArrayOutputStream对象
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // 创建一个缓冲区byte数组
        byte[] buffer = new byte[1024];

        // 从GZIPInputStream对象中读取数据，并将其写入ByteArrayOutputStream对象中，直到没有数据可读
        int len;
        while ((len = gzip.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }

        // 关闭GZIPInputStream对象和ByteArrayInputStream对象
        gzip.close();
        in.close();

        // 获取ByteArrayOutputStream对象中的解压缩后的字节数组
        byte[] data = out.toByteArray();

        // 将字节数组转换为字符串
        return new String(data, StandardCharsets.UTF_8);
    }

}
