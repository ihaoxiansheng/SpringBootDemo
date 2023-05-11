package com.hao.springbootdemo.test.demo4;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xu.liang
 * @since 2023/4/14 16:55
 */
public class DateFormatExample {
    public static void main(String[] args) {


        DateFormatExample();

    }

    public static String DateFormatExample() {
//        String inputDate = "2021.09.10 15:30";
//        String inputDate = "2023/04/14 15:23:20";
        String inputDate = "2023-4-14 15:23";
        System.out.println("inputDate = " + inputDate);
        String formatString = getDateFormat(inputDate);
        System.out.println("Input date format: " + formatString);

        SimpleDateFormat inputFormat = new SimpleDateFormat(formatString);
        try {
            Date date = inputFormat.parse(inputDate);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String outputDate = outputFormat.format(date);
            System.out.println("Output date: " + outputDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inputDate;
    }

    private static String getDateFormat(String inputDate) {
        String formatString = "";
        if (inputDate.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
            formatString = "yyyy-MM-dd HH:mm:ss";
        } else if (inputDate.matches("\\d{4}-\\d{1}-\\d{2} \\d{2}:\\d{2}")) {
            formatString = "yyyy-M-dd HH:mm";
        } else if (inputDate.matches("\\d{4}-\\d{1}-\\d{1} \\d{2}:\\d{2}")) {
            formatString = "yyyy-M-d HH:mm";
        } else if (inputDate.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}")) {
            formatString = "yyyy-MM-dd HH:mm";
        } else if (inputDate.matches("\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
            formatString = "yyyy/MM/dd HH:mm:ss";
        } else if (inputDate.matches("\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}")) {
            formatString = "yyyy/MM/dd HH:mm";
        } else if (inputDate.matches("\\d{4}.\\d{2}.\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
            formatString = "yyyy.MM.dd HH:mm:ss";
        } else if (inputDate.matches("\\d{4}.\\d{2}.\\d{2} \\d{2}:\\d{2}")) {
            formatString = "yyyy.MM.dd HH:mm";
        }
        // add more formats here if needed
        return formatString;
    }

}
