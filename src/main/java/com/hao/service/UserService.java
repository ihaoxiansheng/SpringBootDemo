package com.hao.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hao.dto.User1DTO;
import com.hao.entity.User;

import java.util.List;

/**
 * @author xu.liang
 * @since 2023/5/11 11:03
 */
public interface UserService extends IService<User> {

    void addUser(User user);

    User getUserById(String id);

    /**
     * 分页查询
     *
     * @param user1DTO user1DTO
     * @return PageVO
     */
    Page<User> getPage(User1DTO user1DTO);

    /**
     * 获取用户列表
     *
     * @return List
     */
    List<User> getUserList();

}
