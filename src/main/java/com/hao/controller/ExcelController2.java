package com.hao.controller;

import cn.hutool.core.collection.CollUtil;
import com.hao.entity.User;
import com.hao.service.UserService;
import com.hao.util.MyCsvFileUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xu.liang
 * @since 2024/5/13 15:35
 */
@RestController
@RequestMapping("")
@Slf4j
@Api(tags = "Excel2")
public class ExcelController2 {

    @Resource
    private UserService userService;

    @GetMapping("/createCsvFileJcTest")
    public void createCsvFileJcTest() {
        // 类不确定 随便怎么传都行
        List<User> userList = userService.getUserList();
        if (CollUtil.isEmpty(userList)) {
            return;
        }
        // 需要该目录下存在该文件
        // 存放地址&文件名
        String fileName = "/Users/liang/Desktop/" + MyCsvFileUtil.buildCsvFileFileName(userList);
        // 创建表格行标题
        String tableNames1 = MyCsvFileUtil.buildCsvFileTableNames(userList);
        // 创建表格行标题 新--注解解析字段自定义表格标题名
        String tableNames = MyCsvFileUtil.buildCsvFileTableNamesNew(MyCsvFileUtil.resolveExcelTableName(userList.get(0)));
        // 创建文件
        MyCsvFileUtil.writeFile(fileName, tableNames);
        // 写入数据
        String contentBody = MyCsvFileUtil.buildCsvFileBodyMap(userList);
        // 调用方法生成
        MyCsvFileUtil.writeFile(fileName, contentBody);
    }

}
