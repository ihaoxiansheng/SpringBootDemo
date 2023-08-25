package com.hao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
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
@ApiModel("学生表")
@TableName("student")
public class Student {
    /**
     * 学号
     */
    private Long id;

    /**
     * 名字
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 年级
     */
    private Integer grade;

    /**
     * 专业
     */
    private String major;

    /**
     * 学校
     */
    private String school;

}
