package com.hao.util.mp.injector.methods;

/**
 * 查询满足条件一条数据
 *
 * @author liuchengbiao
 * @date 2021/7/7 1:58 下午
 */

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class SelectOne extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.SELECT_ONE;
        String selectColumn = sqlSelectColumns(tableInfo, true);
        String allColumn = tableInfo.getAllSqlSelect();
        // 修改select * 为 select 具体列名，保证符合上云规范
        selectColumn = selectColumn.replaceFirst("<otherwise>\\*</otherwise>", "<otherwise>" + allColumn + "</otherwise>");
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, String.format(sqlMethod.getSql(),
                sqlFirst(), selectColumn, tableInfo.getTableName(),
                sqlWhereEntityWrapper(true, tableInfo), sqlComment()), modelClass);
        return this.addSelectMappedStatementForTable(mapperClass, getMethod(sqlMethod), sqlSource, tableInfo);
    }
}
