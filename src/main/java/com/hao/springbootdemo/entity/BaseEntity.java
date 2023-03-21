package com.hao.springbootdemo.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xu.liang
 * @since 2023/3/9 10:22
 */
@Data
@ApiModel
public class BaseEntity implements Serializable {

    @TableId
    @ApiModelProperty("主键")
    private String id;

    @TableField(fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty("创建者")
    private String createBy;

    @ApiModelProperty("创建者id")
    @TableField(fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NEVER)
    private String createId;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("更新者")
    private String updateBy;

    @ApiModelProperty("更新者id")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateId;

    @TableField(fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty("创建时间")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("更新时间")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    @TableLogic(delval = "1", value = "0")
    @ApiModelProperty("删除标识(0：正常，1：删除)")
    private Integer delFlag = 0;

}
