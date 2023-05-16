package com.hao.springbootdemo.test.demo5;

import cn.hutool.core.date.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Excel 中的日期是一个数值，由 Excel 自己的规则去定义
 * Excel 中的日期转换成数值的方式
 * 计算当前日期距离 1900年1月0日的天数作为整数部分
 * Excel 把 24 小时当成1，比如 12 时，就是 0.5，所以一天中的某个时刻是 0-1之间的某个小数
 * 最后把整数和小数部分加起来就是当前日期的数值表示
 * 1900 年之前的日期 Excel 会表示为文本，用不了日期函数，可以用加载宏 Extended Date Functions
 *
 * <p>将日期转换成excel中日期对应的数值
 *
 * @author xu.liang
 * @since 2023/5/11 14:17
 */
public class Date2ExcelNumber {

    /**
     * Excel 中日期的计算是从 1900年1月0日开始的 <br>
     * 但是实际日期没有 1 月 0 日 <br>
     * 这个数值是 1900年1月1日0时0分0秒 的时间戳<br>
     */
    private static final long EXCEL_BEGIN_TIME = -2209017600000L;

    /**
     * Excel 中日期数值保留的小数位是 10
     */
    public static final int EXCEL_SCALE = 10;

    /**
     * 24 小时的毫秒数
     */
    private static final long DAY_MILLISECONDS = 1000 * 3600 * 24;

    /**
     * 将日期转换成excel中日期对应的数值
     */
    private static BigDecimal date2ExcelNumber(Date date){
        // 从 1900 年 1 月 0 日（不存在的日期，相当于计算机中经常从 0 计起） 计起，
        // 然后 date 那天也算是 1 天，所以这里要加 2
        BigDecimal days = new BigDecimal((date.getTime() - EXCEL_BEGIN_TIME)/(DAY_MILLISECONDS) + 2);
        // date 那天的 0时0分0秒
        Date begin = DateUtil.beginOfDay(date);
        // excel 把一天的某个时刻当成 0 - 1 之间的一个小数
        BigDecimal time =
                new BigDecimal((date.getTime() - begin.getTime()))
                        // 要设置 scale，否则可能因为无限循环小数而报错
                        .divide(new BigDecimal(DAY_MILLISECONDS), EXCEL_SCALE, BigDecimal.ROUND_HALF_UP);
        return days.add(time);
    }


}
