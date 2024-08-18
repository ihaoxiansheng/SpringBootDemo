package com.hao.config.mq;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 加载公共配置里的队列主题名
 *
 * @author xu.liang
 * @since 2024/5/6 10:03
 */
@Component
public class CommonMqTopicConstant {

    @Resource
    private Environment environment;

    /**
     * 生产者
     */
    public static String P_TEST_PRODUCER;

    /**
     * 日志埋点主题
     */
    public static String LOG_TOPIC;

    /**
     * 订单主题
     */
    public static String ORDER_TOPIC;

    @PostConstruct
    public void init() {
        P_TEST_PRODUCER = environment.getProperty("rocketmq.pTestProducer");
        LOG_TOPIC = environment.getProperty("rocketmq.logTopic");
        ORDER_TOPIC = environment.getProperty("rocketmq.orderTopic");
    }

}
