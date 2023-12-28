package com.hao.controller;

import com.hao.util.SqlRunnerTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xu.liang
 * @since 2023/9/27 22:12
 */
@RestController
@RequestMapping("")
@Slf4j
@Api(tags = "MybatisTest")
public class MybatisTestController {

    private static final String INSERT = "INSERT";
    private static final String UPDATE = "UPDATE";
    private static final String TABLE_CODE = "tableCode";
    private static final String ACTION = "ACTION";
    private static final String DELETE = "DELETE";
    private static final String SYMBOL_1 = "@";
    private static final String ORDER_ID = "ORDER_ID";
    private static final String SHARDING_ID = "SHARDING_ID";

    @Resource
    private SqlRunnerTemplate sqlRunnerTemplate;


    @GetMapping("/MybatisRunnerTest")
    @ApiOperation("MybatisRunnerTest")
    public String MybatisRunnerTest() {

        Map<String, Object> selectOne = this.selectOne("ee_order_qwe", 0L, 0);
        System.out.println("selectOne = " + selectOne);

        List<Map<String, Object>> selectList = this.selectList("ee_order_qwe", 1L, 0);
        System.out.println("selectList = " + selectList);

        return "hello world";
    }

    private Map<String, Object> selectOne(String tableCode, Long orderId, Integer shardingId) {
        try {
            Map<String, Object> map = new HashMap<>(8);
            // 添加表名
            map.put(TABLE_CODE, tableCode);
            // 添加查询动作
            map.put(ORDER_ID, orderId);
            map.put(SHARDING_ID, shardingId);
            return sqlRunnerTemplate.selectOne(map);
        } catch (Exception e) {
            throw e;
        }
    }

    private List<Map<String, Object>> selectList(String tableCode, Long orderId, Integer shardingId) {
        try {
            Map<String, Object> map = new HashMap<>(8);
            // 添加表名
            map.put(TABLE_CODE, tableCode);
            // 添加查询动作
            map.put(ORDER_ID, orderId);
            map.put(SHARDING_ID, shardingId);
            return sqlRunnerTemplate.selecList(map);
        } catch (Exception e) {
            throw e;
        }
    }


}
