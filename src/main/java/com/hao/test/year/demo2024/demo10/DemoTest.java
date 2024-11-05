package com.hao.test.year.demo2024.demo10;

import com.hao.entity.User;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xu.liang
 * @since 2024/10/12 10:39
 */
public class DemoTest {


    public static void main(String[] args) {


        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("李词");
        list.add("王五");
        for (String s : list) {
            System.out.println(s);
        }
        List<String> collect = list.stream().sorted(Collator.getInstance(Locale.CHINA)).collect(Collectors.toList());
        System.out.println("collect = " + collect);


        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setName("张三");
        userList.add(user1);
        User user2 = new User();
        user2.setName("李四");
        userList.add(user2);
        User user3 = new User();
        user3.setName("李词");
        userList.add(user3);
        User user4 = new User();
        user4.setName("王五");
        userList.add(user4);
        userList.add(user4);
        List<User> userCollect = userList.stream().sorted((o1, o2) -> Collator.getInstance(Locale.CHINA).compare(o1.getName(), o2.getName())).collect(Collectors.toList());
        System.out.println("userCollect = " + userCollect);


        String[] arr = {"风险操作申请", "客户故障处理", "故障报告管理", "故障工单", "风险操作申特", "0412", "qwdaqw"};
        Collator collator = Collator.getInstance(Locale.CHINA);
        Arrays.sort(arr, (s1, s2) -> collator.compare(s1, s2));
        for (String s : arr) {
            System.out.println(s);
        }


        int i1 = "12".compareTo("5");
        int i12 = "1".compareTo("2");
        int i21 = "2.1".compareTo("1.2");
        System.out.println("i1 = " + i1);
        System.out.println("i12 = " + i12);
        System.out.println("i21 = " + i21);

        int i = "10.0".compareTo("5.6");
        System.out.println("i = " + i);


        String str1 = "10.0";
        String str2 = "5.6";

        Double num1 = Double.valueOf(str1);
        Double num2 = Double.valueOf(str2);
        int comparisonResult = num1.compareTo(num2);
        if (comparisonResult < 0) {
            System.out.println(str1 + " is less than " + str2);
        } else if (comparisonResult > 0) {
            System.out.println(str1 + " is greater than " + str2);
        } else {
            System.out.println(str1 + " is equal to " + str2);
        }

        //
        // Double aDouble = Double.valueOf("1.0.0");
        // System.out.println("aDouble = " + aDouble);
        // BigDecimal bigDecimal = new BigDecimal("1.0.0");
        // System.out.println("bigDecimal = " + bigDecimal);

        int i111 = "1.0.0".compareTo("1.1");
        System.out.println("i111 = " + i111);


        // String sceneClassifyId = null;
        // List<String> sceneClassifyIdParamList = Arrays.asList(sceneClassifyId.split(","));
        // System.out.println("sceneClassifyIdParamList = " + sceneClassifyIdParamList);

        Set<Map<String, Object>> set = new HashSet<>();
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("a", 1);

        for (int j = 0; j < 3; j++) {
            set.add(hashMap);
        }
        System.out.println("set = " + set);


    }
}
