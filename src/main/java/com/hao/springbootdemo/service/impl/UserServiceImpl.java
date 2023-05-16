package com.hao.springbootdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hao.springbootdemo.dao.UserDao;
import com.hao.springbootdemo.entity.User;
import com.hao.springbootdemo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author xu.liang
 * @since 2023/5/11 11:04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Override
    public void addUser(User user) {
        this.save(user);
    }
}
