package com.hao.springbootdemo.controller;

import cn.hutool.json.JSONUtil;
import com.hao.springbootdemo.util.exception.GlobalExceptionHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    JdbcTemplate jdbcTemplate;

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
