package com.hao.springbootdemo.test.time;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author xu.liang
 * @since 2022/11/16 19:08
 */
public class DateDemo {

    public static void main(String[] args) {

        Date now = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";

        // 新人菜鸟实现
        SimpleDateFormat f = new SimpleDateFormat(strDateFormat);
        System.out.println("SimpleDateFormat:" + f.format(now));

        // java8进阶实现
        LocalDateTime localDateTime = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String result = localDateTime.format(DateTimeFormatter.ofPattern(strDateFormat));
        System.out.println("DateTimeFormatter:" + result);

        // common-lang3老鸟实现
        result = DateFormatUtils.format(now, strDateFormat);
        System.out.println("DateFormatUtils:" + result);

    }
}
