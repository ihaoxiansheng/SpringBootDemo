package com.hao.test.year.demo2024.demo2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * you might not need peek
 *
 * @author xu.liang
 * @since 2024/2/4 14:43
 */
public class noPeekTest {

    public static void main(String[] args) {
        class User {

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "User{" +
                        "name='" + name + '\'' +
                        '}';
            }
        }
        Map<Integer, List<User>> userMap = new HashMap<>();
        userMap.put(1, Stream.generate(User::new).limit(3).collect(Collectors.toList()));

        // 以前的写法
        userMap.put(1, userMap.get(1).stream().peek(u -> u.setName("百度")).collect(Collectors.toList()));

        // 实际上这么写就可以了，因为forEach中进行操作会改变原集合对象
        userMap.get(1).forEach(u -> u.setName("百度"));

        userMap.get(1).forEach(System.out::println);

        // 测试
        List<User> userList = Stream.generate(User::new).limit(3).collect(Collectors.toList());
        System.out.println("userList = " + userList);

    }

}
