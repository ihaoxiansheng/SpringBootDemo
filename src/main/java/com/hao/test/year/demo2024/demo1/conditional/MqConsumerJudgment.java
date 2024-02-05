package com.hao.test.year.demo2024.demo1.conditional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 类是否加载判断
 *
 * @author xu.liang
 * @since 2024/1/26 17:17
 */
@Slf4j
public class MqConsumerJudgment implements Condition {
    public static final String DEVELOPMENT = "dev";

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String environment = null;
        if (context.getEnvironment().getActiveProfiles().length > 0) {
            environment = context.getEnvironment().getActiveProfiles()[0];
        }
        if (DEVELOPMENT.equals(environment)) {
            log.info("=====================当前为开发环境，已禁用队列监听=====================");
            return false;
        }
        return true;
    }
}
