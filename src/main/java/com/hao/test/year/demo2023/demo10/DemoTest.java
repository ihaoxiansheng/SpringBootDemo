package com.hao.test.year.demo2023.demo10;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hao.entity.User;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xu.liang
 * @since 2023/10/10 09:15
 */
public class DemoTest {

    public static void main(String[] args) {


        Map<String, Object> map = new HashMap<>();
        map.put("扩展表", "ee_order_worker");
        map.put("子表", "ee_order_worker");
        System.out.println("map = " + map);

        String string = map.get("扩展表").toString();
        System.out.println("string = " + string);
        map.put("扩展表", "1");
        System.out.println("map = " + map);

        List<String> aaa = new ArrayList<>();
        aaa.add("1");
        aaa.add("2");
        String aastring = aaa.toString();
        System.out.println("aastring = " + aastring);


        List<String> a1 = new ArrayList<>();
        a1.add("1");
        a1.add("2");
        a1.add("3");
        a1.add("4");
        a1.add("5");

        List<String> a2 = new ArrayList<>();
        a1.add("1");
        a1.add("2");
        a1.add("3");

        a2.forEach(a22 -> {
            a1.forEach(a11 -> {
                if (a11.contains(a22)) {
                    //
                }
            });
        });

//        List<String> noneMatch = a1.stream()
//                .filter()
//                .collect(Collectors.toList());
//        System.out.println("noneMatch = " + noneMatch);


        // es查出来的
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId("1");
        user1.setName("1,2");
        userList.add(user1);

        User user11 = new User();
        user11.setId("1");
        user11.setName("1,2");
        userList.add(user11);

        User user111 = new User();
        user111.setId("1");
        user111.setName("2,3");
        userList.add(user111);

        User user1111 = new User();
        user1111.setId("1");
        user1111.setName("1,2,3");
        userList.add(user1111);

        String caseLabel = "1,2";
        List<String> caseLabelList = Arrays.asList(caseLabel.split(","));

        Set<User> setList = new HashSet<>();

        if (StringUtils.isNotBlank(caseLabel)) {
            List<String> list1 = userList.stream().map(User::getName).collect(Collectors.toList());
            System.out.println("        list1 = " + list1);
            for (String s : caseLabelList) {
                System.out.println("        s = " + s);
                List<User> list = userList.stream().filter(e -> e.getName().contains(s)).collect(Collectors.toList());
                System.out.println("        list = " + list);
                setList.addAll(list);
            }
            System.out.println("        set  = " + setList);
//            pageVO.setList(list);
        }


        String gjBeginTime = "2022-03-23 15:07:52";
        int b = Integer.parseInt(gjBeginTime.substring(11, 13)) * 3600
                + Integer.parseInt(gjBeginTime.substring(14, 16)) * 60
                + Integer.parseInt(gjBeginTime.substring(17, 19)) * 60;
        System.out.println("b = " + b);


        List<String> setTest = new ArrayList<>();
        setTest.add("1");
        setTest.add("2");
        setTest.add("5");


        List<String> setTest2 = new ArrayList<>();
        setTest2.add("1");
        setTest2.add("2");
        setTest2.add("3");

        List<String> testList = new ArrayList<>();
        testList.addAll(setTest);
        testList.addAll(setTest2);
        System.out.println("testList = " + testList);

        Set<String> setTest3 = new HashSet<>();
        setTest3.addAll(setTest);
        setTest3.addAll(setTest2);
        System.out.println("setTest3 = " + setTest3);

        List<Map<String, Object>> list1 = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map2.put("orgId", "1");
        map2.put("orgNameSeq", "xxx");
        map2.put("id", "1");
        map2.put("email", "xxx");
        map2.put("userName", "xxx");
        map2.put("userId", "1");
        map2.put("realName", "1");
        map2.put("areaId", "1");
        map2.put("phone", "1");
        map2.put("systemId", null);
        map2.put("asd", null);
        list1.add(map2);
        map1.put("code", 0);
        map1.put("msg", "");
        map1.put("data", list1);
//        JSONObject jsonObject = JSONUtil.parseObj(map1, true);
//        System.out.println("jsonObject = " + jsonObject);
        JSONArray participantData = JSONUtil.parseObj(map1).getJSONArray("data");
        List<Map> result = JSONUtil.toList(participantData, Map.class);
        System.out.println("result = " + result);

        List<Map<String, Object>> list11 = new ArrayList<>();
        Map<String, Object> map11 = new HashMap<>();
        list11.add(map11);
        for (Map<String, Object> stringObjectMap : list11) {
            System.out.println("stringObjectMap = " + stringObjectMap);
        }


        Map<String, Object> testMap = new HashMap<>();
        testMap.put("userName", "xxx");
        testMap.put("userId", "1");
        testMap.put("realName", "1");
        String jsonString = JSON.toJSONString(testMap);
        System.out.println("jsonString = " + jsonString);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        System.out.println("jsonObject = " + jsonObject);
        String userName = jsonObject.get("userName").toString();
        System.out.println("userName = " + userName);


    }


}
