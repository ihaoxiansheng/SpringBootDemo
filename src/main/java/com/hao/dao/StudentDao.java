package com.hao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.entity.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xu.liang
 * @since 2023/5/11 11:02
 */
@Mapper
public interface StudentDao extends BaseMapper<Student> {
}
