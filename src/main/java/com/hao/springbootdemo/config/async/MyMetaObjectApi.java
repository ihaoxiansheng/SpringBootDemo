package com.hao.springbootdemo.config.async;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 实体公共字段填充处理类
 *
 * @author xu.liang
 * @since 2022/4/2 11:34
 */
@Slf4j
@Component
@Primary
public class MyMetaObjectApi implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill...");
        String currentName = "admin";
        this.setFieldValByName("createDate", new Date(), metaObject);
        this.setFieldValByName("updateDate", new Date(), metaObject);
        this.setFieldValByName("createBy", currentName,metaObject);
        this.setFieldValByName("updateBy", currentName, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill...");
        String currentName = "admin";
        this.setFieldValByName("updateDate", new Date(), metaObject);
        this.setFieldValByName("updateBy", currentName, metaObject);
    }
}
