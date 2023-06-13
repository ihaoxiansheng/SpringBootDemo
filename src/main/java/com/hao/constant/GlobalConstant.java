package com.hao.constant;

/**
 * 全局常量
 *
 * @author xu.liang
 * @since 2022/3/30 11:30
 */
public class GlobalConstant {

    /**
     * Status
     */
    public static final Integer DESIGN_ING = 1; // 设计中
    public static final Integer PUBLISHED = 2; // 已发布
    public static final Integer OFF_LINE = 3; // 已下线
    public static final Integer TO_AUDIT = 4; // 待审核
    public static final Integer AUDIT_FAIL = 5; // 审核失败

    /**
     * 是否共有
     */
    public static final Integer IS_PRIVATE = 0; // 私有
    public static final Integer IS_PUBLISH = 1; // 共有

    /**
     * true/false
     */
    public static final String TRUE = "true"; // 正确
    public static final String FALSE = "false"; // 错误

}
