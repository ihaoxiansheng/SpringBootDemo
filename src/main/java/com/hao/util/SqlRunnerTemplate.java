package com.hao.util;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Mybatis SqlRunnerTemplate
 *
 * @author xu.liang
 * @since 2023/9/27 21:35
 */
@Mapper
public interface SqlRunnerTemplate {

    /**
     * 插入
     *
     * @param map sql
     * @return 插入的数量
     */
    @InsertProvider(type = SqlProvider.class, method = "insert")
    int insert(Map<String, Object> map);

    /**
     * 修改
     *
     * @param map sql
     * @return 修改的数量
     */
    @UpdateProvider(type = SqlProvider.class, method = "update")
    int update(Map<String, Object> map);

    /**
     * 删除
     *
     * @param map sql
     * @return 删除的数量
     */
    @DeleteProvider(type = SqlProvider.class, method = "delete")
    int delete(Map<String, Object> map);

    /**
     * 查询一个
     *
     * @param map sql
     * @return map
     */
    @SelectProvider(type = SqlProvider.class, method = "selectOne")
    Map<String, Object> selectOne(Map<String, Object> map);

    /**
     * 查询多个
     *
     * @param map sql
     * @return List
     */
    @SelectProvider(type = SqlProvider.class, method = "selectList")
    List<Map<String, Object>> selecList(Map<String, Object> map);

}
