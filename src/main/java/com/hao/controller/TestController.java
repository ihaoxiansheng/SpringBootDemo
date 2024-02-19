package com.hao.controller;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.hao.util.exception.GlobalExceptionHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Idea开源支持：https://jb.gg/OpenSourceSupport.
 *
 * @author xu.liang
 * @since 2022/9/23 09:42
 */
@RestController
@RequestMapping("")
@Slf4j
@Api(tags = "Test")
public class TestController {

//    @Value("${test.hello1}")
//    private Integer hello1;
//
//    @Value("#{'${test.hello2:}'.split(',')}")
//    private Set<String> hello2;
//
//    @Value("${test.hello3:10000}")
//    private Integer connectTimeout;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private JdbcTemplate jdbcTemplate;

    private final AtomicInteger TIMES = new AtomicInteger(0);

    @GetMapping("/hello")
    @ApiOperation("hello")
    public String test() {
        // List<Map<String, Object>> maps = jdbcTemplate.queryForList("select version();");
        // System.out.println("maps = " + maps);
        return "hello springboot";
    }

    /**
     * 测试全局异常
     *
     * @see GlobalExceptionHandler
     */
    @GetMapping("/add")
    public String add() {
        String result = "成功";
        int a = 1 / 0;
        List<String> list = new ArrayList<>();
        // list.get(0);
        return result;
    }

    @GetMapping("/createNoTest")
    public void createNoTest() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String nowTime = dateFormat.format(new Date());
        StringBuilder key = new StringBuilder("NO_");
        key.append(nowTime);
        Long increment = redisTemplate.opsForValue().increment(key.toString());
        if (Long.valueOf(1).equals(increment)) {
            // 如果获取主键为1则设置主键有效时长为一天86400秒
            redisTemplate.expire(key.toString(), Duration.ofDays(1));

        }
        // 四位数，不够补零
        String primaryNum = String.format("%05d", increment);
        StringBuilder sheetNo = new StringBuilder(nowTime);
        sheetNo.append(primaryNum);
        String no = "M" + sheetNo;
        System.out.println("no = " + no);
    }

    /**
     * 基于短链接参数重定向
     */
    @GetMapping("/{shortKey}")
    public RedirectView redirect(@PathVariable("shortKey") String shortKey, HttpServletRequest request) {
        String userAgentStr = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgentUtil.parse(userAgentStr);
        if ("hello".equals(shortKey)) {
            log.info("短链：【{}】被访问第【{}】次，当前访问浏览器为：{}，操作系统为：{}", shortKey, TIMES.incrementAndGet(), userAgent.getBrowser(), userAgent.getOs());
            if (TIMES.get() < 1) {
                // 第一次跳转官方活动页面(正常逻辑是程序在数据库中维护了一份shortKey和原链接的映射关系表)
                return new RedirectView("https://activity.juejin.cn/rank/2023?utm_campaign=annual_2023&utm_medium=sms&utm_source=yybm1");
            }
            if (TIMES.get() == 2) {
                // 第二次跳转个人拉票页面
                return new RedirectView("https://activity.juejin.cn/rank/2023?writer/3702810893364350");
            }
            // 之后跳转诈骗信息
            return new RedirectView("/cheat");
        }
        return new RedirectView("/notFound");
    }

    @GetMapping("/notFound")
    public String notFound() {
        return "没有找到对应页面";
    }

    @GetMapping("/cheat")
    public String cheat() {
        return "【招贤纳士-专场】500-800元/天，不用坐班，大量要人，时间自由可日结！";
    }


    public static void main(String[] args) {
        int a = 1;
        if (JSONUtil.isJson(String.valueOf(a))) {
            System.out.println("111");
        }
        try {
            // com.alibaba.fastjson.JSON;执行通过
             JSON.parse(String.valueOf(a));
            System.out.println("com.alibaba.fastjson.JSON执行通过");
            // cn.hutool.json.JSONUtil;执行失败
            JSONUtil.parse(String.valueOf(a));
            System.out.println("执行通过");
        } catch (Exception e) {
            System.err.println("cn.hutool.json.JSONUtil执行失败");
            log.error("执行失败：", e);
        }
    }

}
