package com.hao.springbootdemo.util.mp.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.methods.*;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "gc.starter.component", name = "StarterSqlInjector", havingValue = "StarterSqlInjector",matchIfMissing = true)
public class StarterSqlInjector extends DefaultSqlInjector {

    @PostConstruct
    public void init() {
        log.info("--------------------");
        log.info("启动框架默认的MyBatisPlus SQL注入器，解决了 select * 问题；");
        log.info("--------------------");
    }

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        // List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        List<AbstractMethod> methodList = Lists.newArrayList(new Insert(),
                new Delete(),
                new DeleteByMap(),
                new DeleteById(),
                new DeleteBatchByIds(),
                new Update(),
                new UpdateById(),
                new SelectById(),
                new SelectBatchByIds(),
                new SelectByMap(),
                new SelectOne(),
                new SelectCount(),
                new SelectMaps(),
                new SelectMapsPage(),
                new SelectObjs(),
                new SelectList(),
                new SelectPage());
        return methodList;
    }
}
