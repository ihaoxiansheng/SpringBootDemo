package com.hao.util;

import cn.hutool.extra.spring.SpringUtil;
import com.hao.util.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * Spring(Spring boot)工具封装，包括：
 *
 * <ol>
 *     <li>Spring IOC容器中的bean对象获取</li>
 *     <li>注册和注销Bean</li>
 * </ol>
 * 参考 <a href="https://www.cnblogs.com/dongguangming/p/12792789.html">...</a>
 *
 * @author xu.liang
 * @since 2024/4/15 13:39
 */
@Slf4j
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtils.applicationContext == null) {
            log.info("初始化:{},通过该工具类可以实现获取其他组件", this.getClass().getSimpleName());
            SpringContextUtils.applicationContext = applicationContext;
        }
    }

    /**
     * 获取指定名称对象
     *
     * @param name Bean名称
     * @return Bean
     */
    public static Object getBean(String name) {
        try {
            SpringUtil.getBean("");
            return applicationContext.getBean(name);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     * 通过name以及Clazz返回指定的Bean
     *
     * @param <T>   bean类型
     * @param name  Bean名称
     * @param clazz bean类型
     * @return Bean对象
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        try {
            return applicationContext.getBean(name, clazz);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     * 通过class获取Bean
     *
     * @param <T>   Bean类型
     * @param clazz Bean类
     * @return Bean对象
     */
    public static <T> T getBean(Class<T> clazz) {
        try {
            return applicationContext.getBean(clazz);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     * 是否存在指定名称的bean
     *
     * @param name Bean名称
     * @return boolean
     */
    public static boolean containsBean(String name) {
        try {
            return applicationContext.containsBean(name);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return false;
    }

    /**
     * 是否是单例模式
     *
     * @param name Bean名称
     * @return boolean
     */
    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    public static Class<? extends Object> getType(String name) {
        return applicationContext.getType(name);
    }

    public static void removeBeanDefinition(String beanName) {
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) autowireCapableBeanFactory;
        defaultListableBeanFactory.removeBeanDefinition(beanName);
    }

    /**
     * 注册bean
     *
     * @param beanName   Bean名称
     * @param beanXmlDef Bean xml definition
     * @return boolean
     */
    public static boolean registerBean(String beanName, String beanXmlDef) {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext
                .getAutowireCapableBeanFactory();
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<beans xmlns=\"http://www.springframework.org/schema/beans\""
                + "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                + "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
                + "       \">";
        if (StringUtils.isEmpty(beanXmlDef)) {
            throw new GlobalException("Bean的定义不能为空");
        }
        xml = xml + beanXmlDef;
        xml += "</beans>";
        XmlBeanFactory factory = new XmlBeanFactory(new ByteArrayResource(xml.getBytes(StandardCharsets.UTF_8)));
        try {
            if (containsBean(beanName)) {
                beanFactory.removeBeanDefinition(beanName);
            }
        } catch (NoSuchBeanDefinitionException e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        try {
            beanFactory.registerBeanDefinition(beanName, factory.getMergedBeanDefinition(beanName));
            Object obj = applicationContext.getBean(beanName);
            log.info("注册bean:{},{}", beanName, obj == null ? "失败" : "成功");
            return true;
        } catch (Exception e) {
            log.error("注册bean:{}失败：", beanName);
            try {
                beanFactory.removeBeanDefinition(beanName);
            } catch (Exception e1) {
                log.error(ExceptionUtils.getStackTrace(e1));
            }
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return false;
    }

}
