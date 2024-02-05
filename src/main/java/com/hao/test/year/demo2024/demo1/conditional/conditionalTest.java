package com.hao.test.year.demo2024.demo1.conditional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * @author xu.liang
 * @since 2024/1/26 17:19
 */
@Component
@Conditional({MqConsumerJudgment.class})
@Slf4j
public class conditionalTest implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        try {
            // 业务逻辑，当Conditional条件满足时即active不为dev时加载该方法
        } catch (Exception e) {
            log.error("处理异常", e);
        }
    }

}
