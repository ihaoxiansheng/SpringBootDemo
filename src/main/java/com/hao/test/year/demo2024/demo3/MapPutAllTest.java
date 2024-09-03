package com.hao.test.year.demo2024.demo3;

import com.hao.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xu.liang
 * @since 2024/3/25 09:43
 */
public class MapPutAllTest {

    public static void main(String[] args) {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("A", 1);
        map1.put("B", 2);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("C", 3);
        map2.put("D", 4);

        Map<String, Integer> combinedMap = new HashMap<>(map1);
        combinedMap.putAll(map2);

        System.out.println("Map 1: " + map1);
        System.out.println("Map 2: " + map2);
        System.out.println("Combined Map: " + combinedMap);


        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        List<String> list2 = new ArrayList<>();
        list2.add("c");
        list2.add("d");
        list1.addAll(list2);
        System.out.println("list1 = " + list1);

        List<User> userList1 = new ArrayList<>();
        User user1 = new User();
        user1.setId("1");
        user1.setName("11");
        userList1.add(user1);

        List<User> userList2 = new ArrayList<>();
        User user2 = new User();
        user2.setId("1");
        user2.setName("22");
        userList2.add(user1);
        userList2.add(user2);

        List<User> distinctUserList = new ArrayList<>(userList2.stream()
                .collect(Collectors.toMap(User::getId, p -> p, (p1, p2) -> p1)).values());
        System.out.println("distinctUserList = " + distinctUserList);

    }

}
