package com.hao.springbootdemo.common.es;//package com.gccloud.idc.model.common.config.es;
//
//import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//
///**
// * es的属性配置类
// */
//@Configuration
//@ConfigurationProperties(prefix = "elasticsearch")
//@Data
//public class EsConfig {
//
//    /**
//     * ip:端口，多个地址用,号隔开
//     */
//    public String nodes;
//    /**
//     * 协议类型
//     */
//    public String protocol = "http";
//    /**
//     * 用户名
//     */
//    public String userName;
//    /**
//     * 密码
//     */
//    public String password;
//
//    /**
//     * 连接超时时间
//     */
//    private int connectTimeout = 5000;
//
//    /**
//     * Socket 连接超时时间
//     */
//    private int socketTimeout = 5000;
//
//    /**
//     * 获取连接的超时时间
//     */
//    private int connectionRequestTimeout = 5000;
//
//    /**
//     * 最大连接数
//     */
//    private int maxConnectNum = 100;
//
//    /**
//     * 最大路由连接数
//     */
//    private int maxConnectPerRoute = 100;
//
//}
