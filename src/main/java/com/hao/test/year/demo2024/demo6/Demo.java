package com.hao.test.year.demo2024.demo6;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.boot.system.*;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * @author xu.liang
 * @since 2024/6/29 22:43
 */
@Slf4j
public class Demo {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        // 获取当前SpringBoot运行的进程号
        ApplicationPid pid = new ApplicationPid();
        System.out.println("pid = " + pid);
        String pid2 = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        System.out.println("pid2 = " + pid2);

        // ApplicationHome提供访问应用程序主目录的途径。尝试为Jar文件、解压缩文件和直接运行的应用程序选择一个合理的主目录。
        // 应用程序主目录是运行时解压缩的目录，或者Jar文件所在的位置。
        // 通过jar运行后，source输出的是当前运行的jar包路径。
        ApplicationHome home = new ApplicationHome();
        System.out.printf("dir: %s, source: %s%n", home.getDir(), home.getSource());

        // 当前SpringBoot运行时的java版本
        System.out.printf("Java Version: %s%n", JavaVersion.getJavaVersion());

        // 获取临时目录
        ApplicationTemp temp = new ApplicationTemp();
        System.out.printf("临时目录: %s%n", temp.getDir());

        // 获取java.home
        System.out.printf("java.home=%s%n", SystemProperties.get("java.home"));


        // 加载properties文件
        PropertiesPropertySourceLoader propertyLoader = new PropertiesPropertySourceLoader();
        List<PropertySource<?>> properties;
        try {
            properties = propertyLoader.load("cs1", new ClassPathResource("application.properties"));
            System.out.printf("pack.*: %s%n", properties.get(0).getSource());
        } catch (Exception e) {
            log.error("加载properties文件失败", e);
        }
        // 通过这2个Loader非常方便的将资源文件加载，加载后的List<PropertySource>还可以注册到Environment中，在系统中直接访问。
        // 加载yaml文件
        YamlPropertySourceLoader yamlLoader = new YamlPropertySourceLoader();
        List<PropertySource<?>> yaml;
        try {
            yaml = yamlLoader.load("cs2", new ClassPathResource("application.yml"));
            System.out.printf("pack.*: %s%n", yaml.get(0).getSource());
        } catch (Exception e) {
            log.error("加载yaml文件失败", e);
        }

    }
}
