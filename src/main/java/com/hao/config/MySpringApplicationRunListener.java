package com.hao.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class MySpringApplicationRunListener implements SpringApplicationRunListener {

    private static TimeInterval timer = null;

    public MySpringApplicationRunListener(SpringApplication sa, String[] args) {
        timer = DateUtil.timer();
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        log.info("---> [启动耗时监听] 配置文件已经加载（环境属性已经解析），即将进行其他操作。耗时：{} s", timer.intervalRestart() / 1000d);
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("---> [启动耗时监听] 组件、配置等已经加载到 ApplicationContext ，准备初始化 Bean。耗时：{} s", timer.intervalRestart() / 1000d);
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        log.info("---> [启动耗时监听] Bean 扫描耗时：{} s", timer.intervalRestart() / 1000d);
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        log.info("---> [启动耗时监听] 项目启动发生故障时调用，耗时：{} s", timer.intervalRestart() / 1000);
    }

}
