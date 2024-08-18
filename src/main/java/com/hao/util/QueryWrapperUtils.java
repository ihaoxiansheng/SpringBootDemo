package com.hao.util;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;

/**
 * @author xu.liang
 * @since 2024/4/13 10:00
 */
public class QueryWrapperUtils {

    /**
     * 模糊查询
     *
     * @param queryWrapper queryWrapper
     * @param searchKey    查询的关键字，多关键字之间使用空格隔开
     * @param fieldNames   可变参数，多个入参字段
     * @return LambdaQueryWrapper<T>
     */
    @SafeVarargs
    public static <T> LambdaQueryWrapper<T> wrapperLike(LambdaQueryWrapper<T> queryWrapper, String searchKey, SFunction<T, ?>... fieldNames) {
        if (fieldNames == null || fieldNames.length == 0 || StringUtils.isBlank(searchKey)) {
            return queryWrapper;
        }
        String[] searchKeyArr = searchKey.split(" ");
        for (String key : searchKeyArr) {
            if (StringUtils.isBlank(key)) {
                continue;
            }

            queryWrapper.and(wrapper -> {
                for (SFunction<T, ?> fieldName : fieldNames) {
                    if (StringUtils.isBlank(key)) {
                        continue;
                    }
                    wrapper.or().like(fieldName, key);
                }
            });
        }
        return queryWrapper;
    }

    /**
     * 使用Array.newInstance()创建一个安全的泛型数组
     *
     * @param clazz clazz
     * @param index index
     * @param <T>   <T>
     * @return T[]
     */
    public static <T> T[] createArray(Class<T> clazz, int index) {
        return (T[]) Array.newInstance(clazz, index);
    }



}
