package com.hao.controller;

import com.hao.entity.User;
import com.hao.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

//    @PostMapping("/addUser")
    @GetMapping("/addUser")
    @ApiOperation("新增用户")
    public void addUser() {
        System.out.println("hello world");
        User user = new User();
        user.setName("hello world");
        user.setAge("24");
        user.setEmail("123@qq.com");
        userService.addUser(user);
        System.out.println("userService.getById(user.getId()) = " + userService.getById(user.getId()));
    }

}
