package com.hao;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xu.liang
 * @since 2022/9/23 09:42
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan(value = {"com.hao.dao.**"})
@Slf4j
public class SpringBootDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    /**
     * 检查Spring注册的bean
     */
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            String[] beanNames = ctx.getBeanDefinitionNames();
            log.info("Let's inspect the beans provided by Spring Boot: size = {}", beanNames.length);
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                // System.out.println(beanName);
            }
        };
    }

    /**
     * 应用程序在运行时动态获取和更新配置信息
     */
    @Bean
    public CommandLineRunner propertySourcesRunner(ApplicationContext applicationContext) {
        ConfigurableEnvironment environment = (ConfigurableEnvironment) applicationContext.getEnvironment();
        // 设置当前的 active profiles
//        environment.setActiveProfiles();
        // 获取Environment中的PropertySources对象，然后遍历其中的PropertySource对象并打印
        MutablePropertySources propertySources = environment.getPropertySources();
        propertySources.forEach(propertySource -> {
            System.out.println(propertySource);
            System.out.println("==========================");
        });

        Map<String, Object> map1 = new HashMap<>();
        map1.put("test.a", "张三");
        MapPropertySource propertySource1 = new MapPropertySource("propertySource1", map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("test.a", "李四");
        MapPropertySource propertySource2 = new MapPropertySource("propertySource2", map2);

        propertySources.addFirst(propertySource2);
        propertySources.addFirst(propertySource1);

        // 输出张三，把两个addFirst调换位置后，输出李四
        System.out.println(environment.getProperty("test.a"));

        return args -> {
        };
    }

    @javax.annotation.Resource
    ResourceLoader resourceLoader;

    @javax.annotation.Resource
    private Environment env;

    @Override
    public void run(String... args) throws Exception {
        try {
            // 读取启动成功标识
            Resource resource = resourceLoader.getResource("classpath:success.txt");
            List<String> successLineList = IOUtils.readLines(resource.getInputStream(), StandardCharsets.UTF_8);
            for (String line : successLineList) {
//                log.info(line);
            }
        } catch (Exception e) {
            log.error("启动失败，如果您想自定义启动成功标识，您可以在工程src/resources下创建success.txt文件，并写入启动成功输出的信息");
        }
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port", "8080");
        String path = env.getProperty("server.servlet.context-path", "/");
        log.info("本地服务地址: http://localhost:{}{}", port, path);
        log.info("Swagger接口文档地址: http://localhost:{}{}/doc.html", port, path);
    }

    @EnableAsync
    @Configuration
    public static class TaskPoolConfig {
        @Bean("taskExecutor")
        public Executor taskExecutor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(10);
            executor.setMaxPoolSize(20);
            executor.setQueueCapacity(200);
            executor.setKeepAliveSeconds(60);
            executor.setThreadNamePrefix("taskExecutor-");
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            return executor;
        }
    }


}
