package com.hao.springbootdemo.entity;

import lombok.Data;

/**
 * @author xu.liang
 * @since 2022/12/15 15:53
 */
@Data
public class User {

    private String id;

    private String name;

    private String age;

    private String email;

    private UserInfo userInfo;

}
