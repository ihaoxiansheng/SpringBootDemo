package com.hao.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author xu.liang
 * @since 2023/5/6 17:39
 */
@Data
@ToString(callSuper = true)
@ApiModel("公共字段实体类")
public class CommonEntity {

    @ApiModelProperty("创建者id")
    @TableField(value = "CREATE_ID", fill = FieldFill.INSERT)
    private String createId;

    @ApiModelProperty("创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty("更新者id")
    @TableField(value = "UPDATE_ID", fill = FieldFill.INSERT_UPDATE)
    private String updateId;

    @ApiModelProperty("更新者")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private String createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("更新时间")
    @TableField(value = "MODIFY_TIME", fill = FieldFill.INSERT_UPDATE)
    private String updateTime;

    @TableLogic(delval = "1", value = "0")
    @ApiModelProperty("删除标识，默认0:存在，1:已删除")
    private String delFlag;

}
