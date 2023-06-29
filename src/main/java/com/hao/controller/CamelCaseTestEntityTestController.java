package com.hao.controller;

import com.hao.dao.CamelCaseTestDao;
import com.hao.entity.CamelCaseTestEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xu.liang
 * @since 2023/6/26 09:34
 */
@RestController
@RequestMapping("/camelCaseTest")
@Slf4j
@Api(tags = "CamelCaseTest")
public class CamelCaseTestEntityTestController {

    @Resource
    private CamelCaseTestDao camelCaseTestDao;

    @GetMapping("/add")
    @ApiOperation("新增")
    public void add() {
        CamelCaseTestEntity camelCaseTestEntity = new CamelCaseTestEntity();

        camelCaseTestEntity.setUserId("1");
        camelCaseTestEntity.setUsername("哈哈");
        camelCaseTestEntity.setOrgid("2");
        camelCaseTestEntity.setOrgname("好好");
        camelCaseTestDao.insert(camelCaseTestEntity);

        System.out.println("camelCaseTestEntity = " + camelCaseTestEntity);

    }

    @GetMapping("/getById/{id}")
    @ApiOperation("获取")
    public CamelCaseTestEntity getUserById(@PathVariable String id){
        return camelCaseTestDao.selectById(id);
    }


}
