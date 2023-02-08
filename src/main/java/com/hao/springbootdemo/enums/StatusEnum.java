package com.hao.springbootdemo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 状态枚举类
 *
 * @author xu.liang
 * @since 2022/8/12 09:33
 */
@Getter
public enum StatusEnum {
    /**
     * 设计中
     */
    DESIGN_ING(0, "设计中"),
    /**
     * 已发布
     */
    PUBLISHED(1, "已发布"),
    /**
     * 已下线
     */
    OFF_LINE(2, "已下线");

    /**
     * 标记数据库记录值
     */
    @EnumValue
    private final int code;

    /**
     * 标记响应json值
     */
    @JsonValue
    private final String name;

    StatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
    
}
