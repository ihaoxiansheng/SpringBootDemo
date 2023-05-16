package com.hao.springbootdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.springbootdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author xu.liang
 * @since 2023/5/11 10:51
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

    User getUserById(@Param("userId") String userId);
}
