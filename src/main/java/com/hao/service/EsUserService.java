package com.hao.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hao.dto.es.CommonSearchDto;
import com.hao.dto.es.UserSearchDto;
import com.hao.entity.es.UserEntity;

import java.io.IOException;

public interface EsUserService {

    /**
     * es新增用户
     *
     * @param user
     * @return String
     */
    String save(UserEntity user, String indexName);

    /**
     * es用户分页
     *
     * @param dto
     * @return Page<UserEntity>
     */
    Page<UserEntity> page(UserSearchDto dto);

    /**
     * es删除用户
     *
     * @param dto
     * @return Boolean
     */
    Boolean deleteById(CommonSearchDto dto);

    /**
     * 创建索引
     *
     * @param indexName
     * @return Boolean
     */
    Boolean createIndexByRest(String indexName);

    /**
     * 更新
     *
     * @param user
     * @return Boolean
     */
    Boolean update(UserEntity user, String indexName);

    /**
     * 根据id获取用户数据
     *
     * @param dto
     * @return UserEntity
     */
    UserEntity getById(CommonSearchDto dto);

    /**
     * 判断索引是否存在
     *
     * @param indexName
     * @return Boolean
     */
    Boolean existUserIndex(String indexName);

    /**
     * 删除索引
     *
     * @param indexName
     * @return Boolean
     */
    Boolean deleteIndex(String indexName);

}
