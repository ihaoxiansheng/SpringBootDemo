package com.hao.controller;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ulisesbocchio.jasyptspringboot.properties.JasyptEncryptorConfigurationProperties;
import com.ulisesbocchio.jasyptspringboot.util.Singleton;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * jasypt加密配置文件
 *
 * @author xu.liang
 * @since 2024/9/3 16:46
 */
@Configuration
@EnableEncryptableProperties
public class JasyptEncryptorConfiguration {

    // @Value("${jasypt.encryptor.password2:haoxiansheng}")
    // private String password2;
    private static final String password2 = "haoxiansheng";

    @Bean("jasyptStringEncryptor")
    public StringEncryptor jasyptStringEncryptor(Singleton<JasyptEncryptorConfigurationProperties> configProps) {
        JasyptEncryptorConfigurationProperties jasyptProperties = configProps.get();
        String password = jasyptProperties.getPassword();
        if (StringUtils.isEmpty(password)) {
            throw new IllegalStateException("Required Encryption configuration property missing: jasypt.encryptor.password");
        }
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(this.decodePassword(password));
        config.setAlgorithm(jasyptProperties.getAlgorithm());
        config.setKeyObtentionIterations(jasyptProperties.getKeyObtentionIterations());
        config.setPoolSize(jasyptProperties.getPoolSize());
        config.setProviderName(jasyptProperties.getProviderName());
        config.setSaltGeneratorClassName(jasyptProperties.getSaltGeneratorClassname());
        config.setIvGeneratorClassName(jasyptProperties.getIvGeneratorClassname());
        config.setStringOutputType(jasyptProperties.getStringOutputType());
        encryptor.setConfig(config);
        return encryptor;
    }

    // 套娃，对配置文件的秘钥进行加密
    private String decodePassword(String pwd) {
        StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();
        stringEncryptor.setPassword(password2);
        stringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        stringEncryptor.setStringOutputType("base64");
        stringEncryptor.setIvGenerator(new org.jasypt.iv.NoIvGenerator());
        try {
            return stringEncryptor.decrypt(pwd);
        } catch (Exception e) {
            throw new IllegalStateException("jasypt.encryptor.password 有误，无法解析！");
        }
    }

    public static void main(String[] args) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        // jasypt.encryptor.password 加密盐值
        encryptor.setPassword("123");
        // 加密数据库连接地址
        System.out.println(encryptor.encrypt(
                "jdbc:mysql://127.0.0.1:3306/mysql?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&serverTimezone=UTC"));
        // 加密数据库连接用户名
        System.out.println(encryptor.encrypt("user"));
        // 加密数据库连接密码
        System.out.println(encryptor.encrypt("123456"));
        // 加密ip地址
        System.out.println(encryptor.encrypt("127.0.0.1"));
        System.out.println(encryptor.encrypt("haoxiansheng"));
        System.out.println(encryptor.decrypt("CDFdd6W7zzd0j/5/KVxtY1433ifWiBdL"));

        // jasypt.encryptor.password 真实密码
        String pwd = "123";
        StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();
        stringEncryptor.setPassword(password2);
        stringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        stringEncryptor.setIvGenerator(new org.jasypt.iv.NoIvGenerator());
        stringEncryptor.setStringOutputType("base64");
        System.out.println("stringEncryptor.encrypt(pwd) = " + stringEncryptor.encrypt(pwd));

    }

}
