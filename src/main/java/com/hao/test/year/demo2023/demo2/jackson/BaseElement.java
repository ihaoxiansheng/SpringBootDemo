package com.hao.test.year.demo2023.demo2.jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

/**
 * @author xu.liang
 * @since 2023/2/8 11:15
 */
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StartEvent.class, name = "startEvent"),
        @JsonSubTypes.Type(value = EndEvent.class, name = "endEvent")})
public class BaseElement {

    private String id;

    private String type;
}

