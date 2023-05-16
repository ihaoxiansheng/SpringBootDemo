package com.hao.springbootdemo.controller;

import com.hao.springbootdemo.entity.User;
import com.hao.springbootdemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xu.liang
 * @since 2023/5/11 11:14
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/addUser")
    @ApiOperation("新增用户")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

}
