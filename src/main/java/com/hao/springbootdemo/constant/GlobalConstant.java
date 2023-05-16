package com.hao.springbootdemo.constant;

/**
 * 全局常量
 *
 * @author xu.liang
 * @since 2022/3/30 11:30
 */
public interface GlobalConstant {

    /**
     * @Description:
     * @Author yang.hw
     * @Date 2022/3/28 19:23
     */
    interface CurrentUse {
        /**
         * 不在用
         */
        Integer NO = 1;
        /**
         * 在用
         */
        Integer YES = 0;
    }

    interface Status {
        /**
         * 设计中
         */
        Integer DESIGN_ING = 1;
        /**
         * 已发布
         */
        Integer PUBLISHED = 2;
        /**
         * 已下线
         */
        Integer OFF_LINE = 3;
        /**
         * 待审核
         */
        Integer TO_AUDIT = 4;
        /**
         * 审核失败
         */
        Integer AUDIT_FAIL = 5;
    }

    /**
     * 是否共有
     */
    interface IsPrivate {
        /**
         * 私有
         */
        Integer NO = 0;
        /**
         * 共有
         */
        Integer YES = 1;
    }

    /**
     * true/false
     */
    interface TrueFalse {
        /**
         * 正确
         */
        String TRUE = "true";
        /**
         * 错误
         */
        String FALSE = "false";
    }

}
