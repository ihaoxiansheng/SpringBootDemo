package com.hao.test.year.demo2024.demo4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 获取订单编码
 *
 * @author xu.liang
 * @since 2024/4/10 09:31
 */
@Slf4j
public class GetOrderCode {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public static void main(String[] args) {

        GetOrderCode getOrderCode = new GetOrderCode();
        String orderCode = getOrderCode.getCode("CS");
        System.out.println("orderCode = " + orderCode);

    }

    /**
     * 获取订单编码CS2024-0428-0001每月重置后四位
     *
     * @param orderType 订单类型
     * @return String
     */
    public String getCode(String orderType) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        String nowTime = dateFormat.format(new Date());
        StringBuilder orderCode = new StringBuilder(orderType);
        orderCode.append("-").append(nowTime);
        Long num = redisTemplate.opsForValue().increment(orderCode.toString());
        if (Long.valueOf(1).equals(num)) {
            // 获取当前日期
            LocalDate currentDate = LocalDate.now();
            // 获取下个月的第一天日期
            LocalDate nextMonthFirstDay = currentDate.plusMonths(1).withDayOfMonth(1);
            // 计算日期时间差异秒数
            long seconds = ChronoUnit.SECONDS.between(currentDate.atStartOfDay(), nextMonthFirstDay.atStartOfDay());
            // 如果获取主键为1则设置主键有效时长为一天86400秒
            redisTemplate.expire(orderCode.toString(), seconds, TimeUnit.SECONDS);
        }
        // 四位数，不够则填充
        String codeNum = String.format("%04d", num);
        orderCode.append("-").append(codeNum);
        log.info("当前订单编码为：{}", orderCode);
        return orderCode.toString();
    }

}
