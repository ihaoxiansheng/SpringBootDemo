package com.hao.controller;

import com.hao.config.mq.CommonMqTopicConstant;
import com.hao.config.mq.RocketMqAdapter;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xu.liang
 * @since 2024/5/7 09:52
 */
@RestController
@RequestMapping("")
@Slf4j
@Api(tags = "RocketMQ")
public class RocketMQController {

    @Resource
    private RocketMqAdapter rocketMqAdapter;

    @GetMapping("/sendMsg")
    public String sendMsg(String message) {
        rocketMqAdapter.push(CommonMqTopicConstant.P_TEST_PRODUCER, CommonMqTopicConstant.LOG_TOPIC, message);
        rocketMqAdapter.push(CommonMqTopicConstant.P_TEST_PRODUCER, CommonMqTopicConstant.ORDER_TOPIC, message);

        return "success";
    }

}
