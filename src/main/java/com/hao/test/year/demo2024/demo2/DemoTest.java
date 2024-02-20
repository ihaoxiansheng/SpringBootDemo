package com.hao.test.year.demo2024.demo2;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.hao.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xu.liang
 * @since 2024/2/2 10:37
 */
public class DemoTest {

    public static void main(String[] args) {

        // 在单元测试中判断，不满足条件则跳过测试
        // 例如此处就是判断redis是否已连接，为true则会继续往下执行，为false则会skip
//        Assumptions.assumeTrue(false, "Redis is not available. Skipping the test.");

        Map<String, Object> map = new HashMap<>();
        map.put("name", null);
        map.put("age", "2");
        map.put("email", "3");
        CopyOptions copyOptions = new CopyOptions();
        // 忽略null值
        copyOptions.ignoreNullValue();
        User user = BeanUtil.mapToBean(map, User.class, true, copyOptions);
        System.out.println("user = " + user);

    }

}
