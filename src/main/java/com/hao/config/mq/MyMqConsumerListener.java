package com.hao.config.mq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


/**
 * 监听队列，处理业务逻辑
 *
 * @author xu.liang
 * @since 2024/5/6 10:03
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "${rocketmq.orderTopic}", consumerGroup = "C_P_TEST_PRODUCER")
public class MyMqConsumerListener implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt messageExt) {
        try {
            log.info("messageExt = " + messageExt);
            String message = new String(messageExt.getBody());
            log.info("监听Topic消息为：{}", JSON.toJSONString(message));
            if (StringUtils.isNotBlank(message)) {
                // 业务逻辑
            }
            log.info("监听Topic成功...");
        } catch (Exception e) {
            log.error("监听Topic异常...", e);
            if (messageExt.getReconsumeTimes() >= 3) {
                log.error("消息重试次数已超过3次，不再重试...保存到数据库");
            }
        }
    }
}
