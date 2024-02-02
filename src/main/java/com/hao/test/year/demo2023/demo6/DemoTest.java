package com.hao.test.year.demo2023.demo6;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.hao.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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


        String[] a = {"SERVICE_TYPE", "TICKET_MAIN_INFO", "STATUS", "APPLYER", "APPLYTIME", "APPLYUNIT", "SOCTYPE", "MAJOR", "EVENTLEVEL", "AP_ATTACKBEGINTIME", "SPECLEVEL", "AP_DDOSOPTYPE", "AP_ATTACKEDIP", "AP_ASNO", "AP_NETTYPE", "AP_IDCNAME", "AP_ISPNAME", "AP_CUSTNAME", "AP_NETATTACKINGPROV", "AP_ATTACKSUPPLEMENT", "EVENTNAME", "EVENTHAPPENTIME", "PROVINCE", "OTHERSYSID", "PROPERTYIP", "TROUBLEDESC", "BACKUP", "AP_SPAMBEGINTIME", "AP_SPAMIP", "AP_SPAMIPTYPE", "AP_SPAMPROVINCE", "AP_SPAMIDCNAME", "AP_SPAMSUPPLEMENT", "AP_INFOSOURCE", "LEAKREPORTTIME", "AP_TROJANBEGINTIME", "AP_TROJANDEADLINETIME", "SUMMARY"};


        List<User> modelList = new ArrayList<>();
        User user1 = new User();
        user1.setId("1");
        user1.setName("John1");
        user1.setAge("22");
        user1.setEmail("4");
        modelList.add(user1);

        User user2 = new User();
        user2.setId("1");
        user2.setName("John2");
        user2.setAge("22");
        user2.setEmail("4");
        modelList.add(user2);

        User user3 = new User();
        user3.setId("1");
        user3.setName("John3");
        user3.setAge("22");
        user3.setEmail("4");
        modelList.add(user3);

        System.out.println(" ========= ");
        System.out.println("modelList = " + modelList);

        Iterator<User> iterator = modelList.iterator();
        while (iterator.hasNext()) {
            User next = iterator.next();
            if ("John1".equals(next.getName())) {
                iterator.remove();
                continue;
            }
            System.out.println("next = " + next);
        }

        // ConcurrentModificationException
//        for (User u : modelList1) {
//            if ("John1".equals(u.getName())) {
//                modelList1.remove(u);
//                continue;
//            }
//            System.out.println("u = " + u);
//        }
//        System.out.println("modelList1 = " + modelList1);


        final String[] API_PREFIX1 = {"/api/agents/**", "/api/queues/**", "/api/calls/**", "/api/conferences/**"};
        String uri = "/api/agents/list";
        boolean present = Arrays.stream(API_PREFIX1).anyMatch(prefix -> {
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            return antPathMatcher.match(prefix, uri);
        });

        System.out.println("present = " + present);

        String var;
        int varInt;

        double v = Math.random() * 10;
        System.out.println("v = " + v);
        String substring = String.valueOf(v).substring(0, 1);
        System.out.println("substring = " + substring);
        int i = Integer.parseInt(String.valueOf(v).substring(0, 1));
        if (i > 2) {
            var = "2";
            varInt = 2;
        } else {
            var = "1";
            varInt = 1;
        }
        System.out.println("varInt = " + varInt);
        System.out.println("var = " + var);



    }
}
