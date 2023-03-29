package com.hao.springbootdemo.test.demo3;

import cn.hutool.core.map.MapUtil;
import com.hao.springbootdemo.dto.UserDTO;
import org.apache.commons.lang3.Validate;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        List<UserDTO> userList = new ArrayList<>();
        userList.add(userDTO);
        userList.add(userDTO);

        // 要想此处的map编译通过UserDTO对象需加注解 @Accessors(chain = true) ，不加注解也可用peek代替
        List<UserDTO> haha = userList.stream().map(a -> a.setDesc("haha")).collect(Collectors.toList());
        System.out.println("haha = " + haha);

        List<String> streamList = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        System.out.println("streamList = " + streamList);

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
        Map<String, String> mapTo = MapUtil.toCamelCaseMap(map);
        System.out.println("mapTo = " + mapTo);






    }


}
