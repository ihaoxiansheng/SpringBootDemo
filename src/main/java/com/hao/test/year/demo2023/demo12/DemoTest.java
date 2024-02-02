package com.hao.test.year.demo2023.demo12;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @author xu.liang
 * @since 2023/12/1 14:19
 */
public class DemoTest {

    public static void main(String[] args) {


        Date date = new Date();
        String aa = String.valueOf(date);
        System.out.println("aa = " + aa);


        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("userName", "xxx");
        testMap.put("userId", "1");
        testMap.put("realName", "1");
        list.add(testMap);
        list.add(testMap);
        list.add(testMap);


        String string = list.toString();
        System.out.println("string = " + string);

        List<String> list1 = new ArrayList<>();
        // list1.add("1");
        String list1Str = String.join(",", list1);
        System.out.println("list1Str = " + list1Str);

        JSONObject resultMap = JSON.parseObject("");
        System.out.println("resultMap = " + resultMap);


    }


}
