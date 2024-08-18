package com.hao.config.bean;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * 跨域配置
 *
 * @author xu.liang
 * @since 2024/4/18 21:34
 */
@Data
public class Cors {
    private String mapping = "/**";
    private String allowedOrigins = "*";
    private Boolean allowCredentials = true;
    private List<String> allowedMethods = Lists.newArrayList("GET", "POST", "PUT", "DELETE", "OPTIONS");
    private List<String> exposedHeaders;
    private Long maxAge = 3600L;
}
