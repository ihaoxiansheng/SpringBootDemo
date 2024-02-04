package com.hao.test.year.demo2024.demo1;

import cn.hutool.core.io.FileUtil;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author xu.liang
 * @since 2024/1/13 15:52
 */
public class DownLoadTest {

    @SneakyThrows
    public static void main(String[] args) {

        URL url1 = new URL("https://www.baidu.com");
        URLConnection urlConnection = url1.openConnection();
        InputStream inputStream = urlConnection.getInputStream();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder urlString = new StringBuilder();
        String current;
        while ((current = in.readLine()) != null) {
            urlString.append(current);
        }
        System.out.println("urlString = " + urlString);

        String test = "asdfghjkl";
        FileUtil.writeBytes(test.getBytes(), "D:\\222.pdf");


        URL url = new URL("http://www.runoob.com/index.html?language=cn#j2se");
        System.out.println("URL 为：" + url.toString());
        System.out.println("协议为：" + url.getProtocol());
        System.out.println("验证信息：" + url.getAuthority());
        System.out.println("文件名及请求参数：" + url.getFile());
        System.out.println("主机名：" + url.getHost());
        System.out.println("路径：" + url.getPath());
        System.out.println("端口：" + url.getPort());
        System.out.println("默认端口：" + url.getDefaultPort());
        System.out.println("请求参数：" + url.getQuery());
        System.out.println("定位位置：" + url.getRef());

    }
}
