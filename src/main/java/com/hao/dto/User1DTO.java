package com.hao.dto;

import com.hao.entity.UserInfo;
import lombok.Builder;
import lombok.Data;

/**
 * @author xu.liang
 * @since 2023/05/04 15:03
 */
@Data
@Builder(toBuilder = true)
public class User1DTO extends SearchDTO {

    private String id;

    private String name;

    private String age;

    private String email;

    private UserInfo userInfo;

}
