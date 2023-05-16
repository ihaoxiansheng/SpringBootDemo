package com.hao.springbootdemo.dto.es;

//import com.gccloud.sample.elasticsearch.entity.OrgEntity;
//import com.gccloud.sample.elasticsearch.entity.RoleEntity;
//import com.gccloud.starter.common.validator.group.Insert;
//import com.gccloud.starter.common.validator.group.Update;

import com.gccloud.idc.model.common.entity.es.OrgEntity;
import com.gccloud.idc.model.common.entity.es.RoleEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * 用户dto
 */
@Data
public class UserDto {

    @ApiModelProperty("主键")
    @NotBlank(message = "主键不能为空")
    private String id;

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("是否是领导：true是，false否")
    private Boolean isLeader;

    @ApiModelProperty("角色集合")
    private List<RoleEntity> roles;

    @ApiModelProperty("机构信息")
    private OrgEntity org;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("索引名称")
    private String indexName;

}
