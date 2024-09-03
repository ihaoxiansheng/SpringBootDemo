package com.hao.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 查询DTO，其他查询可以继承该类实现查询条件扩展
 *
 * @author xu.liang
 * @since 2024/4/13 10:00
 */
@Data
@ApiModel
public class SearchDTO {
    @ApiModelProperty(notes = "当前显示页数")
    private Integer current;

    @ApiModelProperty(notes = "每页展示数据条数")
    private Integer size;

    @ApiModelProperty(notes = "应用ID")
    private String appId;

    @ApiModelProperty(notes = "查询条件")
    private String searchKey;
}
