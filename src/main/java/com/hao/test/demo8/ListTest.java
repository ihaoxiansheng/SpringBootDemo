package com.hao.test.demo8;

import com.hao.entity.User;

import java.util.*;
import java.util.stream.Collectors;

/**
 * noneMatch, allMatch 和 anyMatch 是 Java 中 Stream API 的 Predicate 接口的两个方法，它们用于对流中的元素进行条件判断。
 * noneMatch：当流中的所有元素都不满足给定的条件时，返回 true；否则返回 false。
 * allMatch：当流中的所有元素都满足给定的条件时，返回 true；否则返回 false。
 * anyMatch：当流中的任意一个元素满足给定的条件时，返回 true；否则返回 false。
 *
 * @author xu.liang
 * @since 2023/8/23 10:11
 */
public class ListTest {

    public static void main(String[] args) {

        User user = new User();
        user.setId("1");
        user.setName("这是111");
        user.setAge("3");
        user.setEmail("4");
        List<User> num1 = new ArrayList<>();
        num1.add(user);

        User user2 = new User();
        user2.setId("1");
        user2.setName("这是222");
        user2.setAge("3");
        user2.setEmail("4");
        User user3 = new User();
        user3.setId("1");
        user3.setName("这是333");
        user3.setAge("3");
        user3.setEmail("4");
        num1.add(user2);
        num1.add(user3);

        User user4 = new User();
        user4.setId("1");
        user4.setName("这是444");
        user4.setAge("3");
        user4.setEmail("4");
        List<User> num2 = new ArrayList<>();
        num2.add(user);
        num2.add(user2);
        num2.add(user3);
        num2.add(user4);

        // 比对num1中的对象不同于num2的对象组装list
        List<User> diff = num1.stream()
                .peek(new1 -> System.out.println("new1：" + new1))
                .filter(new1 -> num2.stream().noneMatch(new2 -> new1.getName().equals(new2.getName())))
                .collect(Collectors.toList());
        System.out.println("diff = " + diff);

        // 比对num1中的对象相同于num2的对象组装list
        List<User> diff2 = num1.stream()
                .peek(new1 -> System.out.println("new1：" + new1))
                .filter(new1 -> num2.stream().anyMatch(new2 -> new1.getName().equals(new2.getName())))
                .collect(Collectors.toList());
        System.out.println("diff2 = " + diff2);

        List<String> a1 = new ArrayList<>();
        a1.add("1");
        a1.add("2");
        a1.add("3");

        List<String> a2 = new ArrayList<>();
        a2.add("1");
        a2.add("2");
        a2.add("3");
        a2.add("4");
        a2.add("5");

        List<String> noneMatch = a2.stream()
                .peek(new1 -> System.out.println("new1：" + new1))
                .filter(new1 -> a1.stream().noneMatch(new2 -> new1.equals(new2)))
                .collect(Collectors.toList());
        System.out.println("noneMatch = " + noneMatch);

        List<String> allMatch = a1.stream()
                .peek(new1 -> System.out.println("new1：" + new1))
                .filter(new1 -> a2.stream().allMatch(new2 -> new1.equals(new2)))
                .collect(Collectors.toList());
        System.out.println("allMatch = " + allMatch);

        List<String> anyMatch = a1.stream()
                .peek(new1 -> System.out.println("new1：" + new1))
                .filter(new1 -> a2.stream().anyMatch(new2 -> new1.equals(new2)))
                .collect(Collectors.toList());
        System.out.println("anyMatch = " + anyMatch);

        List<String> typeList1 = Arrays.asList("1", "2");
        List<String> typeList2 = Arrays.asList("1", "2", "3", "4");
        // 集合列表中全部元素必须在allMatch里面的那些字符串，只要全部元素中有任意一个不同的元素在AllMatch中就返回false
        boolean isMatch1 = typeList1.stream().allMatch(q -> q.equals("1") || q.equals("2") || q.equals("3"));
        boolean isMatch2 = typeList2.stream().allMatch(q -> q.equals("1") || q.equals("2") || q.equals("3"));
        System.out.println("isMatch1 = " + isMatch1);
        System.out.println("isMatch2 = " + isMatch2);

        List<String> aaa1 = typeList1.stream()
                .filter(new1 -> a2.stream().anyMatch(new2 -> new1.equals(new2)))
                .collect(Collectors.toList());
        System.out.println("aaa1 = " + aaa1);

        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");

        for (String key : map.keySet()) {
            System.out.println("key = " + key);
        }
        for (String value : map.values()) {
            System.out.println("value = " + value);
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("entry.getKey() = " + entry.getKey());
            System.out.println("entry.getValue() = " + entry.getValue());
        }

    }
}
