package com.hao.springbootdemo.dto.es;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户查询dto类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchDto {

    @ApiModelProperty(notes = "用户名")
    private String userName;

    @ApiModelProperty(notes = "开始时间")
    private String startTime;

    @ApiModelProperty(notes = "结束时间")
    private String endTime;

    @ApiModelProperty(notes = "id集合")
    private List<String> ids;

    @ApiModelProperty(notes = "备注")
    private String remark;

    @ApiModelProperty(notes = "索引名称")
    private String indexName;

    @ApiModelProperty(notes = "当前显示页数,默认为1")
    private Integer current;

    @ApiModelProperty(notes = "每页展示数据条数，默认为10")
    private Integer size;

    @ApiModelProperty(notes = "查询条件")
    private String searchKey;

}
