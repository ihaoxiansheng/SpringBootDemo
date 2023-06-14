package com.hao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hao.entity.User;

/**
 * @author xu.liang
 * @since 2023/5/11 11:03
 */
public interface UserService extends IService<User> {

    void addUser(User user);

    User getUserById(String id);

}
