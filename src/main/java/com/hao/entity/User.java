package com.hao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hao.service.annotates.ExcelNameAnalyzer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xu.liang
 * @since 2022/12/15 15:53
 */
@Data
@ApiModel("用户表")
@TableName("user")
public class User implements Serializable {

    @ApiModelProperty("主键")
    @TableId(type = IdType.AUTO)
    @ExcelNameAnalyzer(name = "id1")
    private String id = "";

    @ApiModelProperty("名称")
    @ExcelNameAnalyzer(name = "名称1")
    private String name = "";

    @ApiModelProperty("年龄")
    @ExcelNameAnalyzer(name = "年龄1")
    private String age;

    @ApiModelProperty("邮箱")
    @ExcelNameAnalyzer(name = "邮箱1")
    private String email;

    @TableField(exist = false)
    private UserInfo userInfo;

}
