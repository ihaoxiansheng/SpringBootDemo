package com.hao.config.init;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 实现InitializingBean和DisposableBean接口
 *
 * @author xu.liang
 * @since 2023/6/13 15:43
 */
public class MyBean2 implements InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        // 初始化逻辑
    }

    @Override
    public void destroy() throws Exception {
        // 销毁逻辑
    }

}
