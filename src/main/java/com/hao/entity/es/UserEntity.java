package com.hao.entity.es;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * 用户信息实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity {

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


}
