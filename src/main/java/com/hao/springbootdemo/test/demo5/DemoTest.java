package com.hao.springbootdemo.test.demo5;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author xu.liang
 * @since 2023/5/10 16:35
 */
public class DemoTest {

    public static void main(String[] args) {


        test1("11");

    }

    @SneakyThrows
    public static void test1(String testSet) {
        System.out.println("testSet = " + testSet);
        testSet = "test";
        System.out.println("testSet = " + testSet);
        


        Map<String, String> map = new HashMap<>();
        map.put("1", "11");
        map.put("2", "22");

        Set<String> strings = map.keySet();
        System.out.println("strings = " + strings);

        // 假设Excel中的日期数值为"2023/5/10 0:43"
        String dateStr = "2023/5/10 0:43";

        // 定义日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        // 将日期字符串解析为日期对象
        Date date = sdf.parse(dateStr);

        // 将日期对象转换为指定格式的字符串
        String formattedDate = sdf.format(date);
        System.out.println(formattedDate); // 输出结果为：2023-05-10T00:43:52Z


    }

}
