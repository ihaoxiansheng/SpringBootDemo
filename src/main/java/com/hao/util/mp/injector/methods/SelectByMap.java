package com.hao.util.mp.injector.methods;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.Map;

public class SelectByMap extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.SELECT_BY_MAP;
        String sqlSelectColumns = sqlSelectColumns(tableInfo, false);
        if (StringUtils.equals(StringPool.STAR, sqlSelectColumns)) {
            // 修改select * 为 select 具体列名，保证符合上云规范
            sqlSelectColumns = tableInfo.getAllSqlSelect();
        }
        String sql = String.format(sqlMethod.getSql(), sqlSelectColumns,
                tableInfo.getTableName(), sqlWhereByMap(tableInfo));
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, Map.class);
        return this.addSelectMappedStatementForTable(mapperClass, getMethod(sqlMethod), sqlSource, tableInfo);
    }
}
