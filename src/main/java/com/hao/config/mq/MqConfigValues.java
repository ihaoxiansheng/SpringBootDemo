package com.hao.config.mq;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 用于加载封装MQ的配置项
 *
 * @author xu.liang
 * @since 2024/5/6 10:03
 */
@Data
@Configuration(value = "MqConfigValues")
@ConfigurationProperties(prefix = "spring.rocketmq")
@ApiModel("用于加载封装MQ的配置项")
public class MqConfigValues {

    /**
     * 地址列表 ip:port;ip:port;ip:port
     */
    @ApiModelProperty("地址列表 ip:port;ip:port;ip:port")
    public String address;

    /**
     * 用户
     */
    @ApiModelProperty("用户")
    public String authId;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    public String authPwd;

    /**
     * 集群名称
     */
    @ApiModelProperty("集群名称")
    public String clusterName;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    public String tenantId;

    /**
     * 生产者集合
     */
    @ApiModelProperty("生产者集合")
    public List<String> producerList;

    /**
     * TOPIC集合
     */
    @ApiModelProperty("TOPIC集合")
    public List<String> topicList;

    /**
     * PUSH一次消费最大数量 默认值1
     */
    @ApiModelProperty("PUSH一次消费最大数量")
    public int consumeMessageBatchMaxSize = 1;
}
