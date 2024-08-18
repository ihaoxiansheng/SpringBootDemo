package com.hao.config.mq;

/**
 * @author xu.liang
 * @since 2024/5/6 10:03
 */
public interface RocketMqAdapter {

    /**
     * 创建队列生成者
     * @param producerName 生产者名称
     */
    void createProducer(String producerName);

    /**
     * 消息入队
     * @param producerName 生产者名称
     * @param topicName    要送入的数据的队列名
     * @param value        要送入的数据
     */
    void push(String producerName, String topicName, String value);
}
