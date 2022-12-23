package com.hao.springbootdemo.util.mp.injector.methods;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 查询满足条件所有数据
 *
 * @author hubin
 * @since 2018-04-06
 */
@Slf4j
public class SelectList extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.SELECT_LIST;
        String sql = String.format(sqlMethod.getSql(), sqlFirst(), sqlSelectColumns(tableInfo, true), tableInfo.getTableName(),
                sqlWhereEntityWrapper(true, tableInfo), sqlComment());
        String allColumn = tableInfo.getAllSqlSelect();
        // 修改select * 为 select 具体列名，保证符合上云规范
        sql = sql.replaceFirst("<otherwise>\\*</otherwise>", "<otherwise>" + allColumn + "</otherwise>");
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addSelectMappedStatementForTable(mapperClass, getMethod(sqlMethod), sqlSource, tableInfo);
    }

    public static void main(String[] args) {
        String sql = "<script><choose>\n" +
                "<when test=\"ew != null and ew.sqlFirst != null\">\n" +
                "${ew.sqlFirst}\n" +
                "</when>\n" +
                "<otherwise></otherwise>\n" +
                "</choose> SELECT <choose>\n" +
                "<when test=\"ew != null and ew.sqlSelect != null\">\n" +
                "${ew.sqlSelect}\n" +
                "</when>\n" +
                "<otherwise>*</otherwise>\n" +
                "</choose> FROM gc_menu \n" +
                "<where>";
        sql = sql.replaceFirst("<otherwise>\\*</otherwise>", "<otherwise>1,2,3,4</otherwise>");
        System.out.println(sql);
    }
}
