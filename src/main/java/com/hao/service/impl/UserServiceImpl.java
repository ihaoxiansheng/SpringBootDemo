package com.hao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hao.dao.UserDao;
import com.hao.service.UserService;
import com.hao.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xu.liang
 * @since 2023/5/11 11:04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void addUser(User user) {
        this.save(user);
    }

    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

}
