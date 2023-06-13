// package com.hao.springbootdemo.enums;
//
// import com.baomidou.mybatisplus.core.enums.IEnum;
// import com.fasterxml.jackson.annotation.JsonFormat;
// import com.fasterxml.jackson.annotation.JsonValue;
//
// /**
//  * 状态枚举类
//  *
//  * @author xu.liang
//  * @since 2022/8/12 09:33
//  */
// @JsonFormat(shape = JsonFormat.Shape.OBJECT)
// public enum StatusEnum implements IEnum<Integer> {
//     /**
//      * 设计中
//      */
//     DESIGN_ING(0, "设计中"),
//     /**
//      * 已发布
//      */
//     PUBLISHED(1, "已发布"),
//     /**
//      * 已下线
//      */
//     OFF_LINE(2, "已下线");
//
//     /**
//      * 标记数据库记录值
//      */
//     private final int code;
//
//     /**
//      * 标记响应json值
//      */
//     private final String name;
//
//     StatusEnum(int code, String name) {
//         this.code = code;
//         this.name = name;
//     }
//
//     @Override
//     public Integer getValue() {
//         return this.code;
//     }
//
//     @JsonValue
//     public String getName() {
//         return this.name;
//     }
//
//     /**
//      * 枚举字段传入中文值时，转换为数据库记录值
//      */
//     public static StatusEnum getEnum(String name) {
//         for (StatusEnum item : values()) {
//             if (item.getName().equals(name)) {
//                 return item;
//             }
//         }
//         return null;
//     }
// }
