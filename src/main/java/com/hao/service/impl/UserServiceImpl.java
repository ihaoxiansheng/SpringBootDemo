package com.hao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hao.dao.UserDao;
import com.hao.dto.User1DTO;
import com.hao.entity.User;
import com.hao.service.UserService;
import com.hao.util.QueryWrapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public Page<User> getPage(User1DTO user1DTO) {
        LambdaQueryWrapper<User> queryWrapper = QueryWrapperUtils.wrapperLike(
                new LambdaQueryWrapper<>(), user1DTO.getSearchKey(),
                User::getName, User::getAge, User::getEmail);
        queryWrapper.eq(StringUtils.isNotBlank(user1DTO.getId()), User::getId, user1DTO.getId());
        queryWrapper.orderByAsc(User::getId);
        Page<User> page = new Page<>(user1DTO.getCurrent(), user1DTO.getSize());
        return userDao.selectPage(page, queryWrapper);
    }

    public List<User> getUserList() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        return userDao.selectList(queryWrapper);
    }

}
