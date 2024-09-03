package com.hao.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hao.dto.User1DTO;
import com.hao.entity.User;
import com.hao.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

//    @PostMapping("/addUser")
    @GetMapping("/addUser")
    @ApiOperation("新增用户")
    public void addUser() {
        System.out.println("hello world");
        User user = new User();
        user.setId("1");
        user.setName("hello world");
        user.setAge("24");
        user.setEmail("123@qq.com");
        userService.addUser(user);
        System.out.println("userService.getById(user.getId()) = " + userService.getById(user.getId()));
    }

    @GetMapping("/getUserById/{id}")
    @ApiOperation("获取用户")
    public User getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }

    @GetMapping("/getPage")
    @ApiOperation("分页获取用户")
    public Page<User> pageList(@ApiParam User1DTO user1DTO){
        return userService.getPage(user1DTO);
    }

}
