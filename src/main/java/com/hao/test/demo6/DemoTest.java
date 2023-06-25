package com.hao.test.demo6;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.hao.entity.User;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xu.liang
 * @since 2023/6/5 10:46
 */
public class DemoTest {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("6月");

        DateTime str = DateUtil.parse("1970-01-01 00:00:00");
        DateTime str2 = DateUtil.parse("1970-01-01");
        System.out.println("str = " + str);
        System.out.println("str2 = " + str2);

        DateTime str3 = DateUtil.parse("");
        System.out.println("str3 = " + str3);


        String test = null;
        String aa = "null";
        String bb = "false";
        String cc = "true";
        System.out.println("StringUtils.equals(test, aa) = " + StringUtils.equals(test, aa));
        if (StringUtils.equals(test, aa)) {
            System.out.println("相等");
        } else {
            System.out.println("不相等");
        }

        boolean equalscc = !cc.equals(test);
        System.out.println("equalscc = " + equalscc);

        boolean equalsAny = StrUtil.equalsAny("aa", "aa1", "aaaa", "das");
        System.out.println("equalsAny = " + equalsAny);

        boolean equalsaa = StrUtil.equals(test, aa);
        System.out.println("equalsaa = " + equalsaa);
        boolean equalsbb = StrUtil.equals(test, bb);
        System.out.println("equalsbb = " + equalsbb);


        if (StrUtil.isEmpty(test)) {
            System.out.println("1相等");
        } else {
            System.out.println("1不相等");
        }


//        StopWatch stopWatch = new StopWatch("测试");
//        stopWatch.start("测试");
//        Thread.sleep(1000);
//        stopWatch.stop();
//        System.out.println("stopWatch.getTotalTimeMillis() = " + stopWatch.getTotalTimeMillis());
//        System.out.println("stopWatch.getTotalTimeNanos() = " + stopWatch.getTotalTimeNanos());
//        System.out.println("stopWatch.getTotalTimeSeconds() = " + stopWatch.getTotalTimeSeconds());
//        // 任务1
//        stopWatch.start("任务一");
//        Thread.sleep(1000);
//        stopWatch.stop();
//        // 任务2
//        stopWatch.start("任务一");
//        Thread.sleep(2000);
//        stopWatch.stop();
//        // 打印出耗时
//        System.out.println(stopWatch.prettyPrint());

        // String.format() 可用于字符串自动补位
        // 0代表前面补充0，5代表长度为5，d代表参数为正数型
        String num = String.format("%05d", 11);
        System.out.println("num = " + num);

        String digit1 = "5";
        String digit2 = "9";

        int codePoint1 = Character.codePointAt(digit1, 0);
        int codePoint2 = Character.codePointAt(digit2, 0);

        if (codePoint1 < codePoint2) {
            System.out.println(digit1 + " comes before " + digit2);
        } else if (codePoint1 > codePoint2) {
            System.out.println(digit1 + " comes after " + digit2);
        } else {
            System.out.println(digit1 + " and " + digit2 + " are equal");
        }

        List<Integer> list = new ArrayList<>();
        for (Integer integer : list) {
            // 不会打印
            System.out.println("integer = " + integer);
        }


        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId("1");
        user.setName("John");
        user.setAge("22");
        user.setEmail("4");

        userList.add(user);
        userList.add(user);

        System.out.println("userList1 = " + userList);
        for (User user1 : userList) {
            user1.setName("hh");
        }
        System.out.println("userList2 = " + userList);


    }
}
