package com.hao.service;//package com.gccloud.idc.model.service.service;
//
//import com.gccloud.idc.model.common.dto.es.CommonSearchDto;
//import com.gccloud.idc.model.common.dto.es.UserSearchDto;
//import com.gccloud.idc.model.common.entity.es.UserEntity;
//import com.gccloud.starter.mybatis.page.PageVO;
//
//import java.io.IOException;
//
//public interface EsUserService {
//
//    /**
//     * es新增用户
//     *
//     * @param user
//     * @return
//     */
//    String save(UserEntity user, String indexName);
//
//    /**
//     * es用户分页
//     *
//     * @param dto
//     * @return
//     */
//    PageVO<UserEntity> page(UserSearchDto dto);
//
//    /**
//     * es删除用户
//     *
//     * @param dto
//     */
//    Boolean deleteById(CommonSearchDto dto);
//
//    /**
//     * 创建索引
//     *
//     * @throws IOException
//     */
//    Boolean createIndexByRest(String indexName);
//
//    /**
//     * 更新
//     *
//     * @param user
//     */
//    Boolean update(UserEntity user, String indexName);
//
//    /**
//     * 根据id获取用户数据
//     *
//     * @param dto
//     * @return
//     */
//    UserEntity getById(CommonSearchDto dto);
//
//    /**
//     * 判断索引是否存在
//     *
//     * @param indexName
//     * @return
//     * @throws Exception
//     */
//    Boolean existUserIndex(String indexName);
//
//    /**
//     * 删除索引
//     *
//     * @param indexName
//     * @throws Exception
//     */
//    Boolean deleteIndex(String indexName);
//
//}
