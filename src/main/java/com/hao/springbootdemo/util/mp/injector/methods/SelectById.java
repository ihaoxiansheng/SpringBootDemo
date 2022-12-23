package com.hao.springbootdemo.util.mp.injector.methods;

/**
 * @author liuchengbiao
 * @date 2021/7/7 1:34 下午
 */

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;

/**
 * 根据ID 查询一条数据
 *
 * @author hubin
 * @since 2018-04-06
 */
@Slf4j
public class SelectById extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.SELECT_BY_ID;
        String sql = sqlSelectColumns(tableInfo, false);
        if (StringUtils.equals(StringPool.STAR, sql)) {
            // 修改select * 为 select 具体列名，保证符合上云规范
            sql = tableInfo.getAllSqlSelect();
        }
        SqlSource sqlSource = new RawSqlSource(configuration, String.format(sqlMethod.getSql(), sql,
                tableInfo.getTableName(), tableInfo.getKeyColumn(), tableInfo.getKeyProperty(),
                tableInfo.getLogicDeleteSql(true, true)), Object.class);
        return this.addSelectMappedStatementForTable(mapperClass, getMethod(sqlMethod), sqlSource, tableInfo);
    }

    public static void main(String[] args) {
        System.out.println("*".equals("1234"));
        System.out.println("*".equals("*"));
    }
}
