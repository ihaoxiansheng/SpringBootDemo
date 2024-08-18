package com.hao.config;

import com.hao.config.bean.Cors;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author xu.liang
 * @since 2024/4/18 21:34
 */
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "hao")
@Data
public class GlobalConfig {

    /**
     * 跨域设置
     */
    @NestedConfigurationProperty
    private Cors cors = new Cors();

}
