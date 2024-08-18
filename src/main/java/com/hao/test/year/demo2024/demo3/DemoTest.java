package com.hao.test.year.demo2024.demo3;

import cn.hutool.core.lang.Console;
import com.hao.entity.User;
import org.joda.time.DateTime;

import java.util.*;

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

        // 返回当前日期时间加上指定秒数的日期
        Date date = DateTime.now().plusSeconds(3600).toDate();
        System.out.println("date = " + date);


        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("1", 1);
        hashMap.put("2", 2);
        hashMap.put("3", 3);
        String string = hashMap.get("2").toString();
        System.out.println("string = " + string);
        hashMap.remove("2");
        System.out.println("hashMap = " + hashMap);

        // 当一个对象被声明为final时，它表示该对象的引用不可变，也就是说，
        // 一旦引用被初始化后，就不能再指向其他对象。换句话说，final修饰的对象引用始终指向同一个对象。
        final User finalUser = new User();
        finalUser.setId("1");
        finalUser.setName("2");
//        finalUser = new User();  // 编译不通过
        // 但可以修改对象属性
        System.out.println("finalUser = " + finalUser);

        Integer integer = Console.lineNumber();
        System.out.println("integer = " + integer);
        // 打印进度条
        Console.printProgress('#', 100, 0.2);
        System.out.println();

    }

}
