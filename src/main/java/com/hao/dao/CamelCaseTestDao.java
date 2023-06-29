package com.hao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hao.entity.CamelCaseTestEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xu.liang
 * @since 2023/6/26 09:36
 */
@Mapper
public interface CamelCaseTestDao extends BaseMapper<CamelCaseTestEntity> {
}
