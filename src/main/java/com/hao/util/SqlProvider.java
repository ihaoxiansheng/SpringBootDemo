package com.hao.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Mybatis SqlProvider
 *
 * @author xu.liang
 * @since 2023/9/27 21:35
 */
@Slf4j
public class SqlProvider {

    /**
     * INSERT SQL
     *
     * @param map
     * @return sql
     */
    public String insert(Map<String, Object> map) {
        String tableName = (String) map.get("tableCode");

        List<String> colList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();
        map.keySet().forEach(key -> {
            if ("tableCode".equals(key) || "ACTION".equals(key)) {
                return;
            }
            colList.add(key);
            valueList.add("#{" + key + "}");
        });
        String insertSql = new SQL().INSERT_INTO(tableName)
                .INTO_COLUMNS(String.join(",", colList))
                .INTO_VALUES(String.join(",", valueList)).toString();

        log.info("sql:{},params:{}", insertSql, map);
        return insertSql;
    }

    /**
     * UPDATE SQL
     *
     * @param map
     * @return sql
     */
    public String update(Map<String, Object> map) {
        String tableName = (String) map.get("tableCode");
        Map<String, Object> setMap = (Map) map.get("setMap");
        Map<String, Object> conditionMap = (Map) map.get("conditionMap");
        List<String> cols = new ArrayList<>();
        List<String> conditions = new ArrayList<>();
        setMap.keySet().forEach(key -> {
            cols.add(key + " = #{setMap." + key + "}");
        });
        conditionMap.keySet().forEach(key -> {
            conditions.add(key + " = #{conditionMap." + key + "}");
        });
        String[] strings = conditions.toArray(new String[conditions.size()]);
        String updateSql = new SQL().UPDATE(tableName)
                .SET(String.join(",", cols))
                .WHERE(strings).toString();
        log.info("sql:{},params:{}", updateSql, map);
        return updateSql;
    }

    /**
     * DELETE SQL
     *
     * @param map
     * @return sql
     */
    public String delete(Map<String, Object> map) {

        String tableName = (String) map.get("tableCode");
        List<String> conditions = new ArrayList<>();
        map.keySet().forEach(key -> {
            if ("tableCode".equals(key) || "ACTION".equals(key)) {
                return;
            }
            conditions.add(key + " = #{" + key + "}");
        });
        String[] strings = conditions.toArray(new String[conditions.size()]);
        String deleteSql = new SQL().DELETE_FROM(tableName)
                .WHERE(strings).toString();
        log.info("sql:{},params:{}", deleteSql, map);
        return deleteSql;
    }

    /**
     * SELECT SQL
     *
     * @param map
     * @return sql
     */
    public String selectOne(Map<String, Object> map) {
        String tableName = (String) map.get("tableCode");
        List<String> conditions = new ArrayList<>();
        for (String key : map.keySet()) {
            if ("tableCode".equals(key)) {
                continue;
            }
            conditions.add(key + " = #{" + key + "}");
        }
        String[] array = conditions.toArray(new String[conditions.size()]);
        String selectOne = new SQL().SELECT("*")
                .FROM(tableName)
                .WHERE(array).toString();
        log.info("sql:{},params:{}", selectOne, map);
        return selectOne;
    }

    /**
     * SELECT SQL
     *
     * @param map
     * @return sql
     */
    public String selectList(Map<String, Object> map) {
        String tableName = (String) map.get("tableCode");
        List<String> conditions = new ArrayList<>();
        for (String key : map.keySet()) {
            if ("tableCode".equals(key) || "ACTION".equals(key)) {
                continue;
            }
            conditions.add(key + " = #{" + key + "}");
        }
        String[] where = conditions.toArray(new String[0]);
        String selectList = new SQL().SELECT("*")
                .FROM(tableName)
                .WHERE(where).toString();
        log.info("sql:{},params:{}", selectList, map);
        // 不完整写法。。。
//        String sql = new SQL().SELECT("*")
//                .FROM(tableName).LEFT_OUTER_JOIN()
//                .WHERE(where).LIMIT("").ORDER_BY().toString();

        return selectList;
    }

}
