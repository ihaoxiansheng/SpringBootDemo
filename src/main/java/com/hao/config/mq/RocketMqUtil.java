package com.hao.config.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xu.liang
 * @since 2024/5/6 10:03
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "spring.rocketmq", name = "operationMode", havingValue = "NATIVE")
public class RocketMqUtil implements RocketMqAdapter {

    @Resource
    private MqConfigValues mqConfigValues;

    /**
     * 生产者MAP
     */
    public static Map<String, DefaultMQProducer> producerMap = new HashMap<>();

    @PostConstruct
    public void initMQ() throws Exception {
        try {
            log.info("RocketMQ连接初始化...");
            log.info("开始创建生产者...");
            List<String> producerList = mqConfigValues.getProducerList();
            if (producerList != null) {
                for (String producerName : producerList) {
                    log.info("生产者【{}】创建开始", producerName);
                    createProducer(producerName);
                }
            }
            log.info("创建生产者结束...");
            log.info("MQ连接初始化结束...");
        } catch (Exception e) {
            throw new Exception("MQ连接初始化异常", e);
        }
    }

    /**
     * 创建队列生成者
     *
     * @param producerName 生产者名称
     */
    @Override
    public void createProducer(String producerName) {
        // 生产者名称
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr(mqConfigValues.address);
        producer.setProducerGroup(producerName);
        producer.setSendMsgTimeout(10000);
        producer.setRetryTimesWhenSendFailed(0);
        producerMap.put(producerName, producer);
        try {
            producer.start();
        } catch (Exception e) {
            log.error("【{}】生产者连接异常，", producerMap, e);
        }
    }

    /**
     * 消息入队
     *
     * @param producerName 生产者名称
     * @param topicName    要送入的数据的队列名
     * @param value        要送入的数据
     */
    @Override
    public void push(String producerName, String topicName, String value) {
        Message message = new Message(topicName, null, value.getBytes());
        SendResult sendResult;
        try {
            sendResult = producerMap.get(producerName).send(message);
            if (sendResult != null && sendResult.getSendStatus() == SendStatus.SEND_OK) {
                log.info("消息主题【{}】，消息内容【{}】，入队成功", topicName, value);
            } else {
                log.error("入队失败");
            }
        } catch (Exception e) {
            log.error("入队异常，重试一次", e);
            try {
                sendResult = producerMap.get(producerName).send(message);
            } catch (Exception e1) {
                log.error("入队异常，重试一次，依然异常，消息【{}】暂时丢弃", value, e1);
            }
        }
    }

}
