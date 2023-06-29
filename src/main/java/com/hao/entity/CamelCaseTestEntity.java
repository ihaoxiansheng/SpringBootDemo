package com.hao.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xu.liang
 * @since 2023/6/26 09:29
 */
@Data
@ApiModel("驼峰命名测试表")
@TableName("camel_case_test")
public class CamelCaseTestEntity {

    @ApiModelProperty("主键")
    @TableId(type = IdType.AUTO)
    private String id;

    @ApiModelProperty("用户id")
    @TableField(value = "USERID")
    private String userId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("机构id")
    private String orgid;

    @ApiModelProperty("机构名称")
    private String orgname;

    @TableLogic(delval = "1", value = "0")
    @ApiModelProperty("删除标识，0：正常；1：删除")
    private Integer delFlag = 0;

}
