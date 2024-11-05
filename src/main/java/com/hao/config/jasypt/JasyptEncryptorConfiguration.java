package com.hao.config.jasypt;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ulisesbocchio.jasyptspringboot.properties.JasyptEncryptorConfigurationProperties;
import com.ulisesbocchio.jasyptspringboot.util.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.salt.NoOpIVGenerator;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * jasypt配置文件秘钥加密配置类
 *
 * @author xu.liang
 * @since 2024/9/3 16:46
 */
@Configuration
@EnableEncryptableProperties
@Slf4j
public class JasyptEncryptorConfiguration {

    // @Value("${jasypt.encryptor.password2:haoxiansheng}")
    // private String password2;

    private static final String password2 = "haoxiansheng";

    /**
     * StringEncryptor 加密解密实现类
     * beanName 必须为 jasyptStringEncryptor 才能使自定义的生效，因为官方会校验
     * 或者在配置文件中配置加密的beanName，例如：jasypt.encryptor.bean 指定所使用的加密加密类的名称
     *
     * @param configProps jasypt框架默认读取配置类
     * @return StringEncryptor
     */
    @Bean("jasyptStringEncryptor")
    public StringEncryptor jasyptStringEncryptor(Singleton<JasyptEncryptorConfigurationProperties> configProps) {
        JasyptEncryptorConfigurationProperties jasyptProperties = configProps.get();
        String password = jasyptProperties.getPassword();
        if (StringUtils.isEmpty(password)) {
            log.error("配置文件缺少配置 jasypt.encryptor.password 请检查！");
            throw new IllegalStateException("Required Encryption configuration property missing: jasypt.encryptor.password");
        }
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(this.decryptPassword(password));
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

    /**
     * 解密配置文件秘钥
     *
     * @param password 配置文件秘钥加密后的值
     * @return 秘钥
     */
    private String decryptPassword(String password) {
        StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();
        stringEncryptor.setPassword(password2);
        stringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        stringEncryptor.setStringOutputType("base64");
        stringEncryptor.setIVGenerator(new NoOpIVGenerator());
        try {
            return stringEncryptor.decrypt(password);
        } catch (Exception e) {
            log.error("jasypt.encryptor.password 配置错误解密秘钥失败，应配置秘钥加密后的值");
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
        System.out.println();
        String pwd = "123";
        System.out.println("真实密码pwd = " + pwd);
        StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();
        stringEncryptor.setPassword(password2);
        stringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        stringEncryptor.setIVGenerator(new NoOpIVGenerator());
        stringEncryptor.setStringOutputType("base64");
        System.out.println("加密后pwd = " + stringEncryptor.encrypt(pwd));


        StandardPBEStringEncryptor stringEncryptor2 = new StandardPBEStringEncryptor();
        stringEncryptor2.setPassword("2WsNqliJ");
        stringEncryptor2.setAlgorithm("PBEWithMD5AndDES");
        stringEncryptor.setIVGenerator(new NoOpIVGenerator());
        stringEncryptor2.setStringOutputType("base64");
        // salt：2WsNqliJ   真实密码：Sy7Fly<X2UG}
        String decrypt = stringEncryptor2.decrypt("almwG91maAZBgJcm40F+b7k8PFsVZHIp");
        System.out.println("decrypt = " + decrypt);
    }

}
