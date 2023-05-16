package com.hao.springbootdemo.constant;

/**
 * elasticsearch常用数据类型
 */
public interface FieldTypeConstant {
    /**
     * 字符串类型，对应java中的string
     * 支持分词，全文检索,支持模糊、精确查询,不支持聚合,排序操作
     * test类型最大支持的字符长度无限制,适合大字段存储；
     */
    String TEXT = "text";
    /**
     * 字符串类型，对应java中的string
     * 不进行分词，直接索引，支持模糊、支持精确匹配，支持聚合、排序操作
     * keyword类型的最大支持的长度为——32766个UTF-8类型的字符
     */
    String KEYWORD = "keyword";
    /**
     * 数字类型, 对应java中的Long类型
     */
    String LONG = "long";
    /**
     * 数字类型, 对应java中的integer类型
     */
    String INTEGER = "integer";
    /**
     * 数字类型, 对应java中的double类型
     */
    String DOUBLE = "double";
    /**
     * 日期类型，对应java中的date类型
     * 可以设置日期格式（如yyyy-MM-dd HH:mm:ss）
     * yyyy-MM-dd
     * epoch_millis（毫秒值）
     */
    String DATE = "date";

    /**
     * 日期类型格式(目前es的date数据类型仅支持这几种格式)
     */
    interface DateFormat {
        /**
         * 年-月-日 时:分:秒
         */
        String BASIC_FORMAT = "yyyy-MM-dd HH:mm:ss";

        /**
         * 年-月-日
         */
        String YMD_FORMAT = "yyyy-MM-dd";


        /**
         * 毫秒值
         */
        String EPOCH_MILLIS = "epoch_millis";


        /**
         * 三种格式组合
         */
        String GROUP_FORMAT = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis";

    }

    /**
     * 布尔类型，对应java中的boolean类型
     */
    String BOOLEAN = "boolean";
    /**
     * 对象类型，可以存储实体对象和集合
     */
    String OBJECT = "object";

}
