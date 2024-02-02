package com.hao.test.year.demo2023.demo2.jackson;

import cn.hutool.core.io.file.FileReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Jackson使用@JsonSubTypes实现多态解析，当接口实现很多JsonSubTypes注解十分臃肿且违反开闭原则(OCP)
 * 解决步骤：1、给子类加JsonTypeName注解；2、借助reflections框架，将所有JsonTypeName注解类扫描出来；3、手动将扫出来的类注册到ObjectMapper对象
 *
 * @author xu.liang
 * @since 2023/2/8 11:18
 */
public class JackSonTest {

    @Test
    public void version1Json2Test() {
        // 默认UTF-8编码，可以在构造中传入第二个参数做为编码
        FileReader fileReader = new FileReader("json2.json");
        String jsonString = fileReader.readString();
        // System.out.println("jsonString = " + jsonString);
        ObjectMapper mapper = new ObjectMapper();
        try {
            StartEvent startEvent = (StartEvent) mapper.readValue(jsonString, BaseElement.class);
            System.out.println("startEvent = " + startEvent);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void version1Json1Test() {
        FileReader fileReader = new FileReader("json1.json");
        String jsonString = fileReader.readString();
        // System.out.println("jsonString = " + jsonString);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<BaseElement> baseElements = objectMapper.readValue(jsonString, new TypeReference<List<BaseElement>>() {
            });
            baseElements.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 有点问题
    // @Test
    // public void version2Json2Test() {
    //     Reflections reflections = new Reflections("com.hao.springbootdemo.test.demo2.jackson");
    //     Set<Class<? extends BaseElement>> classSet = reflections.getSubTypesOf(BaseElement.class);
    //     ObjectMapper mapper = new ObjectMapper();
    //     classSet.forEach(mapper::registerSubtypes);
    //     FileReader fileReader = new FileReader("json2.json");
    //     String jsonString = fileReader.readString();
    //     try {
    //         StartEvent startEvent = (StartEvent) mapper.readValue(jsonString, BaseElement.class);
    //         System.out.println(startEvent);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
    //
    // @Test
    // public void version2Json1Test() {
    //     Reflections reflections = new Reflections("com.hao.springbootdemo.test.demo2.jackson");
    //     Set<Class<? extends BaseElement>> classSet = reflections.getSubTypesOf(BaseElement.class);
    //     ObjectMapper mapper = new ObjectMapper();
    //     classSet.stream().forEach(clazz -> mapper.registerSubtypes(clazz));
    //     FileReader fileReader = new FileReader("json1.json");
    //     String jsonString = fileReader.readString();
    //     try {
    //         List<BaseElement> result = mapper.readValue(jsonString,
    //                 new TypeReference<List<BaseElement>>() {
    //                 });
    //         result.forEach(System.out::println);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }


}
