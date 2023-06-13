package com.hao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author xu.liang
 * @since 2022/12/15 15:53
 */
@Data
@TableName("user")
public class User {

    private String id;

    private String name;

    private String age;

    private String email;

    @TableField(exist = false)
    private UserInfo userInfo;

}
