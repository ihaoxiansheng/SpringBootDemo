package com.hao.config.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 项目启动后初始化操作
 *
 * @author xu.liang
 * @since 2023/6/13 15:43
 */
@Component
@Slf4j
@Order(2)
public class ApplicationRunnerInitCacheData implements ApplicationRunner {

//    @Resource
//    private xxx xxx;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(">>>>>>>>>>>>>项目启动完毕，初始化xxx数据 开始<<<<<<<<<<<<<");

        // 业务代码

        log.info(">>>>>>>>>>>>>项目启动完毕，初始化xxx数据 结束<<<<<<<<<<<<<");
    }

}
