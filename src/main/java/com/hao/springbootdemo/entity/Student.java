package com.hao.springbootdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xu.liang
 * @since 2022/3/16 10:55
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class Student {
    /**
     * 学号
     */
    private long id;

    private String name;

    private int age;

    /**
     * 年级
     */
    private int grade;

    /**
     * 专业
     */
    private String major;

    /**
     * 学校
     */
    private String school;

}
