package com.hao.controller;

import cn.hutool.json.JSONUtil;
import com.hao.util.exception.GlobalExceptionHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static void main(String[] args) {
        int a = 1;
        if (JSONUtil.isJson(String.valueOf(a))) {
            System.out.println("111");
        }
        try {
            // com.alibaba.fastjson.JSON;执行通过
            // JSON.parse(String.valueOf(a));
            // cn.hutool.json.JSONUtil;执行失败
            JSONUtil.parse(String.valueOf(a));
            System.out.println("执行通过");
        } catch (Exception e) {
            System.out.println("执行失败");
            e.printStackTrace();
        }
    }

}
