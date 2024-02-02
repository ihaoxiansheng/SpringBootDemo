package com.hao.test.year.demo2023.demo3;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * map驼峰转下划线
 *
 * @author xu.liang
 * @since 2023/3/21 09:25
 */
public class CamelCaseToSnakeCase {

    // 下划线
    public static final PropertyNamingStrategy SNAKE_CASE = new PropertyNamingStrategy.SnakeCaseStrategy();
    // 大驼峰
    public static final PropertyNamingStrategy UPPER_CAMEL_CASE = new PropertyNamingStrategy.UpperCamelCaseStrategy();
    // 小驼峰
    public static final PropertyNamingStrategy LOWER_CAMEL_CASE = new PropertyNamingStrategy();
    // ......
    public static final PropertyNamingStrategy LOWER_CASE = new PropertyNamingStrategy.LowerCaseStrategy();
    public static final PropertyNamingStrategy KEBAB_CASE = new PropertyNamingStrategy.KebabCaseStrategy();


    /**
     * 驼峰映射的对象转换成下划线形式的对象
     */
    public static <T> Map<String, T> objectToMap(Map<String, T> map) {
        Map<String, T> resultMap = new HashMap<>();
        PropertyNamingStrategy SNAKE_CASE = PropertyNamingStrategy.SNAKE_CASE;
        for (Map.Entry<String, T> entry : map.entrySet()) {
            resultMap.put(((PropertyNamingStrategy.SnakeCaseStrategy) SNAKE_CASE).translate(entry.getKey()), entry.getValue());
        }
        return resultMap;
    }


    public static void main(String[] args) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("xmName", "测试");
            map.put("nameAuthor", "simple");
            Map<String, String> map1 = objectToMap(map);
            System.out.println(map1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("转换失败");
        }
    }


}
