package com.hao.config.mq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;


/**
 * 监听队列，处理业务逻辑
 *
 * @author xu.liang
 * @since 2024/5/6 10:03
 */
@Slf4j
@Component
public class MyMqConsumerListener2 implements ApplicationRunner {

    @Resource
    private MqConfigValues mqConfigValues;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            log.info("开始创建消费组：{}", "C_" + CommonMqTopicConstant.LOG_TOPIC);
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("C_" + CommonMqTopicConstant.LOG_TOPIC);
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
            consumer.setNamesrvAddr(mqConfigValues.address);
            consumer.subscribe(CommonMqTopicConstant.LOG_TOPIC, "*");
            consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
                try {
                    for (MessageExt messageExt : list) {
                        String message = new String(messageExt.getBody(), StandardCharsets.UTF_8);
                        log.info("监听Topic消息为：{}", JSON.toJSONString(message));
                        if (StringUtils.isNotBlank(message)) {
                            // 业务逻辑
                        }
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                } catch (Exception e) {
                    log.error("监听Topic异常...", e);
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            });
            consumer.start();
        } catch (Exception e) {
            log.error("监听Topic异常...", e);
        }
    }
}
