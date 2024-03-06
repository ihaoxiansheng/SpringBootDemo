package com.hao.test.year.demo2024.demo3;

import com.hao.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xu.liang
 * @since 2024/3/1 09:21
 */
public class DemoTest {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setName("1");
        user.setAge("1");
        user.setEmail("1");
        list.add(user);
        User user1 = new User();
        user1.setName("2");
        user1.setAge("2");
        user1.setEmail("2");
        list.add(user1);
        User user2 = new User();
        user2.setName("3");
        user2.setAge("3");
        user2.setEmail("3");
        list.add(user2);
        for (User u : list) {
            u.setName("zz");
        }
        // 测试for循环修改集合对象
        System.out.println("list = " + list);
    }

}
