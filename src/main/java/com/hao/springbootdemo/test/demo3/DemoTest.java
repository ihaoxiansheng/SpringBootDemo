package com.hao.springbootdemo.test.demo3;

import cn.hutool.core.map.MapUtil;
import com.hao.springbootdemo.dto.UserDTO;
import org.apache.commons.lang3.Validate;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xu.liang
 * @since 2023/3/8 11:07
 */
public class DemoTest {

    public static void main(String[] args) {

        String host = "localhost";
        String[] hosts = host.split(",");
        System.out.println("Arrays.asList(hosts) = " + Arrays.asList(hosts));

        UserDTO userDTO = new UserDTO();
        userDTO.setId("1");
        userDTO.setUsername("hello");
        userDTO.setPassword("123456");
        userDTO.setEmail("123");
        userDTO.setPhone("18888888888");
        userDTO.setAddress("北京");
        userDTO.setDesc("");

        // String test = null;
        // String isNull = Validate.notNull(test, "xxx cannot be null");
        // System.out.println("isNull = " + isNull);
        //
        // String isNull1 = Validate.notNull(test);
        // System.out.println("isNull1 = " + isNull1);
        //
        // String isNull2 = Validate.notBlank(test);
        // System.out.println("isNull2 = " + isNull2);
        //
        // String isNull3 = Validate.notEmpty(test);
        // System.out.println("isNull3 = " + isNull3);

        List<Integer> asList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> asList1 = Arrays.asList(1, 2, 3, 10, 5, 6);
        if (asList.containsAll(asList1)) {
            System.out.println("包含");
        } else {
            System.out.println("不包含");
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("user_name", "name");
        map.put("teacher_name", "id");
        // map的key下划线转驼峰
        Map<String, String> map1 = MapUtil.toCamelCaseMap(map);
        System.out.println("map1 = " + map1);

    }


}
