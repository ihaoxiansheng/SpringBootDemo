package com.hao.test.time;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * @author xu.liang
 * @since 2022/11/16 19:08
 */
public class DateDemo {

    /**
     * SimpleDateFormat是线程不安全的类，一般不要定义为 static 变量，如果定义为static，必须加锁
     * 或者使用 DateUtils 工具类 线程安全
     */
    private static final ThreadLocal<DateFormat> DATE_FORMAT = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static void main(String[] args) {

        // 新人菜鸟实现
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("SimpleDateFormat = " + sdf.format(new Date()));

        // java8进阶实现
        LocalDateTime localDateTime = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String result = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("DateTimeFormatter = " + result);

        // common-lang3老鸟实现
        result = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        System.out.println("DateFormatUtils = " + result);

        DateFormat dateFormat = DATE_FORMAT.get();
        String format = dateFormat.format(new Date());
        System.out.println("format = " + format);

        System.out.println("=================================");
        // Date转String
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        // Date转英文年月日星期时间 Fri 23 December 2022 16:08 PM
        System.out.println(localDateTime1.format(DateTimeFormatter.ofPattern("E dd MMMM yyyy HH:mm a", Locale.US)));
        // Date转中文年月日星期时间 2022年十二月23日 星期五 16:08 下午
        System.out.println(localDateTime1.format(DateTimeFormatter.ofPattern("yyyy年MMMMdd日 E HH:mm a", Locale.CHINA)));
        // String转Date Fri Dec 23 15:52:00 CST 2022
        String dateStr = "2022年十二月23日 星期五 15:52 下午";
        LocalDateTime parse = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy年MMMMd日 E HH:mm a", Locale.CHINA));
        Date toDate = Date.from(parse.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(toDate);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format1 = localDateTime1.format(formatter1);
        System.out.println("format1 = " + format1);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        System.out.println("当前时间，当天的开始时间(日期+时分秒)：" + todayStart.format(dtf));
        System.out.println("当前时间，当天的结束时间(日期+时分秒)：" + todayEnd.format(dtf));
        System.out.println("当前的时间(只有日期)" + LocalDate.now());


        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        String formattedDate = formatter.format(LocalDate.now());
        String formattedZonedDate = formatter.format(ZonedDateTime.now());
        System.out.println("LocalDate          : " + formattedDate);
        System.out.println("formattedZonedDate : " + formattedZonedDate);
        LocalDateTime dateTime = LocalDateTime.now();
        // 20180303
        String strDate1 = dateTime.format(DateTimeFormatter.BASIC_ISO_DATE);
        // 2013-03-03
        String strDate2 = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
        // 当前时间
        String strDate3 = dateTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
        // 2018-03-03
        String strDate4 = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // 今天是：2018年 三月 03日 星期六
        String strDate5 = dateTime.format(DateTimeFormatter.ofPattern("今天是：YYYY年 MMMM dd日 E", Locale.CHINESE));
        System.out.println(strDate1);
        System.out.println(strDate2);
        System.out.println(strDate3);
        System.out.println(strDate4);
        System.out.println(strDate5);
        // 将一个字符串解析成一个日期对象
        String strDate6 = "2018-03-03";
        String strDate7 = "2017-03-03 15:30:05";
        LocalDate date = LocalDate.parse(strDate6, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime dateTime1 = LocalDateTime.parse(strDate7, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(date);
        System.out.println(dateTime1);
    }
}
