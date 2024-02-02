package com.hao.test.year.demo2023.demo12;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Function接口测试
 *
 * @author xu.liang
 * @since 2023/12/12 09:46
 */
public class FunctionTest {

    public static void main(String[] args) {

        ParamDto dto = new ParamDto();
        dto.setFruitOrVegetable("1");
        dto.setBuy("2");
        test(dto);

    }


    public static Map<String, Object> test(ParamDto dto) {
        // 水果、蔬菜
        String fruitOrVegetable = dto.getFruitOrVegetable();
        // 要买的东西
        String buy = dto.getBuy();
        Map<String, Object> returnMap = new HashMap<>();

        // 创建方法映射
        Map<String, MyFunction<ParamDto, String>> methodMapping = new HashMap<>();
        methodMapping.put("1:1", FunctionTest::getString1);
        methodMapping.put("1:2", FunctionTest::getString2);
        methodMapping.put("1:4", FunctionTest::getString3);
        methodMapping.put("2:1", FunctionTest::getString4);
        methodMapping.put("2:2", FunctionTest::getString5);
        methodMapping.put("2:4", FunctionTest::getString6);

        // 根据条件执行对应的方法
        String key = fruitOrVegetable + ":" + buy;
        if (methodMapping.containsKey(key)) {
            ParamDto resourceApiDto = new ParamDto();
            BeanUtils.copyProperties(dto, resourceApiDto);
            MyFunction<ParamDto, String> method = methodMapping.get(key);
            System.out.println("method = " + method);
            String invokeApiResult = method.apply(resourceApiDto);
            System.out.println("invokeApiResult = " + invokeApiResult);
        }
        return returnMap;
    }

    @Data
    public static class ParamDto {
        private String fruitOrVegetable;
        private String buy;
    }

    public static String getString1(ParamDto dto) {
        return "1";
    }

    public static String getString2(ParamDto dto) {
        return "2";
    }

    public static String getString3(ParamDto dto) {
        return "3";
    }

    public static String getString4(ParamDto dto) {
        return "4";
    }

    public static String getString5(ParamDto dto) {
        return "5";
    }

    public static String getString6(ParamDto dto) {
        return "6";
    }

    /**
     * 声明函数式接口
     *
     * @param <T> 请求入参
     * @param <R> 返回结果
     */
    @FunctionalInterface
    public interface MyFunction<T, R> {
        R apply(T t);
    }

}
