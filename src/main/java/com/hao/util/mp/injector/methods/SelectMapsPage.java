package com.hao.util.mp.injector.methods;

/**
 * 查询满足条件所有数据（并翻页）
 *
 * @author liuchengbiao
 * @date 2021/7/7 2:51 下午
 */

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.Map;

public class SelectMapsPage extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.SELECT_MAPS_PAGE;
        String sql = String.format(sqlMethod.getSql(), sqlFirst(), sqlSelectColumns(tableInfo, true),
                tableInfo.getTableName(), sqlWhereEntityWrapper(true, tableInfo), sqlComment());
        String allColumn = tableInfo.getAllSqlSelect();
        // 修改select * 为 select 具体列名，保证符合上云规范
        sql = sql.replaceFirst("<otherwise>\\*</otherwise>", "<otherwise>" + allColumn + "</otherwise>");
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addSelectMappedStatementForOther(mapperClass, getMethod(sqlMethod), sqlSource, Map.class);
    }
}
