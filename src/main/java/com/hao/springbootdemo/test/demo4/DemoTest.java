package com.hao.springbootdemo.test.demo4;

import com.alibaba.fastjson.JSON;
import com.hao.springbootdemo.dto.UserDTO;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * equals比较
 *
 * @author xu.liang
 * @since 2023/4/11 16:56
 */
public class DemoTest {

    @SneakyThrows
    public static void main(String[] args) {

        // equals比较
        UserDTO userDTO = new UserDTO();
//        userDTO.setId("1");
//        if (userDTO.getDesc().equals("1")) { // 报错NullPointerException
        if ("1".equals(userDTO.getDesc())) { // 正常运行 ps：常量放在equals左边就不需要判断是否为null
            System.out.println("null");
        }


        LocalDate now = LocalDate.now();
        // 2023-04-11
        System.out.println("now = " + now);


        String hello = "varchar(20)";
        int index = hello.indexOf("(");
        String h = hello.substring(0, index);
        System.out.println("h = " + h);


        String sizeStr = "hello world";
        double size = (double) sizeStr.getBytes().length / (1024 * 1024);
        System.out.println("字符串大小：" + size + " MB");


        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        long count = list.stream().peek(System.out::println).count();
        System.out.println(count);

        Date leftStartDate = new Date();
        System.out.println("leftStartDate = " + leftStartDate.getTime());
        Thread.sleep(200);
        Date nowTime = new Date();
        System.out.println("nowTime.getTime()); = " + nowTime.getTime());
        long longTime = (nowTime.getTime() - leftStartDate.getTime()) / (1000 * 60);
        System.out.println("longTime = " + longTime);


        System.out.println("hello world");

        test1();
//        System.out.println("test1() = " + test1());

    }

    public static void test1() {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        for (Integer integer : list) {
            if (integer == 2) {
//                return;
                continue;
            }
            System.out.println("integer = " + integer);
//            return "1";
        }
        System.out.println("11111111111111");
//        return "ok";

    }

}
