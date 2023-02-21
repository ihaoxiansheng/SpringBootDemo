package com.hao.springbootdemo.test.demo2.jackson;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.ToString;

/**
 * @author xu.liang
 * @since 2023/2/8 11:15
 */
@ToString(callSuper = true)
@Data
public class StartEvent extends BaseElement{

    private String startEvent;

    private String startInfo;
}
