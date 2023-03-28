package com.hao.springbootdemo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author xu.liang
 * @since 2023/3/11 23:00
 */
@Data
@Accessors(chain = true)
public class UserDTO {

    @NotNull(message = "id不能为空")
    private String id;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "邮箱不能为空")
    private String email;

    @NotBlank(message = "手机号不能为空")
    private String phone;

    @NotBlank(message = "地址不能为空")
    private String address;

    @NotBlank(message = "描述不能为空")
    private String desc;

}
