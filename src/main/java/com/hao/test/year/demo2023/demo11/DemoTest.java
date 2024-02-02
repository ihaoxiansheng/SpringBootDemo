package com.hao.test.year.demo2023.demo11;

import cn.hutool.core.util.ObjectUtil;
import com.hao.entity.User;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xu.liang
 * @since 2023/11/2 19:41
 */
public class DemoTest {

    public static void main(String[] args) {

        if (ObjectUtil.equal("null", "1")) {
            System.out.println("111111111111111");

        }
        if (ObjectUtil.equal("null", 0L)) {
            System.out.println("2222222222222");

        }
        if (ObjectUtil.equal("null", null)) {
            System.out.println("333333333");

        }
        if (ObjectUtil.equal("null", "null")) {
            System.out.println("44444444444444");

        }

        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId("1");
        user1.setName("nice");
        userList.add(user1);

        User user3 = new User();
        user3.setId("3");
        user3.setName("pretty");
        userList.add(user3);

        User user2 = new User();
        user2.setId("2");
        user2.setName("good");
        userList.add(user2);

        System.out.println("userList = " + userList);
        List<User> sortUserByIdList = userList.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
        System.out.println("sortUserByIdList = " + sortUserByIdList);


        List<String> collect = userList.stream().map(User::getName).collect(Collectors.toList());
        System.out.println("collect = " + collect);
        String name = String.join("、", collect);
        System.out.println("name = " + name);

        System.out.println("userList = " + userList);
        List<String> nameList1 = userList.stream().map(User::getName).collect(Collectors.toList());
        System.out.println("nameList1 = " + nameList1);

        userList = new ArrayList<>();
        System.out.println("userList = " + userList);
        List<String> nameList2 = userList.stream().map(User::getName).collect(Collectors.toList());
        System.out.println("nameList2 = " + nameList2);


        String name1 = "上海联通.网络智慧运营中心.运营调度室";

        String name2 = name1.replaceAll("\\.", "-->");
        System.out.println("name2 = " + name2);


        String endTime = getAfterThreeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println("endTime = " + endTime);

    }

    /**
     * 获取三天后下午五点的时间
     *
     * @param beginTime 开始时间
     * @return String
     */
    public static String getAfterThreeTime(String beginTime) {
        String feedbackTime = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(beginTime));
            // 获取当前时间为周几
            int weekIdx = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            String planEndTimeStr = beginTime.substring(0, 10);
            planEndTimeStr += " 00:00:00";
            date = sdf.parse(planEndTimeStr);
            Calendar dd = Calendar.getInstance();
            dd.setTime(date);//设置传来的时间
            if (3 == weekIdx || 4 == weekIdx || 5 == weekIdx) {
                // 5天后
                dd.add(Calendar.DATE, 5);
                dd.add(Calendar.HOUR, 17);
                feedbackTime = sdf.format(dd.getTime());
            } else if (6 == weekIdx) {
                // 4天后
                dd.add(Calendar.DATE, 4);
                dd.add(Calendar.HOUR, 17);
                feedbackTime = sdf.format(dd.getTime());
            } else {
                // 3天后
                dd.add(Calendar.DATE, 3);
                dd.add(Calendar.HOUR, 17);
                feedbackTime = sdf.format(dd.getTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedbackTime;
    }

}
