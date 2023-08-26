package com.hao.test.demo8;

import com.hao.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * noneMatch测试
 *
 * @author xu.liang
 * @since 2023/8/23 10:11
 */
public class ListTest {

    public static void main(String[] args) {

        User user = new User();
        user.setId("1");
        user.setName("这是2");
        user.setAge("3");
        user.setEmail("4");
        List<User> num1 = new ArrayList<>();
        num1.add(user);

        User user2 = new User();
        user2.setId("1");
        user2.setName("这是22");
        user2.setAge("3");
        user2.setEmail("4");
        num1.add(user2);

        User user3 = new User();
        user3.setId("1");
        user3.setName("这是222");
        user3.setAge("3");
        user3.setEmail("4");
        List<User> num2 = new ArrayList<>();
        num2.add(user);
        num2.add(user3);
        num2.add(user3);

        // 比对num1中的对象不同于num2的对象
        List<User> diff = num1.stream()
                .peek(new1 -> System.out.println("new1：" + new1))
                .filter(new1 -> num2.stream().noneMatch(new2 -> new1.getName().equals(new2.getName())))
                .collect(Collectors.toList());
        System.out.println("diff = " + diff);

    }
}
