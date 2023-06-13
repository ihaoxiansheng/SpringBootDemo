package com.hao.test.demo2.lombok;

import lombok.*;

import java.util.Set;

/**
 * lombok测试注解@Builder
 *
 * @author xu.liang
 * @since 2023/2/7 16:41
 */
@Data
@Builder
public class User {

    private String name;

    private Integer age;

    /**
     * Singular，对集合类属性可以单个赋值，默认属性名要以“s”结尾，也可以指定赋值方法名@Singular(“赋值方法名”)
     */
    @Singular
    private Set<String> skills;

    public static void main(String[] args) {

        User user = User.builder()
                .name("hello")
                .age(18)
                .skill("吃")
                .skill("喝")
                .skill("玩")
                .skill("乐")
                .build();

        System.out.println(user);

    }
}
