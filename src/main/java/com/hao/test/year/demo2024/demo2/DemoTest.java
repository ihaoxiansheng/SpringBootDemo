package com.hao.test.year.demo2024.demo2;

import org.junit.jupiter.api.Assumptions;
/**
 * @author xu.liang
 * @since 2024/2/2 10:37
 */
public class DemoTest {

    public static void main(String[] args) {

        // 在单元测试中判断，不满足条件则跳过测试
        // 例如此处就是判断redis是否已连接，为true则会继续往下执行，为false则会skip
        Assumptions.assumeTrue(false, "Redis is not available. Skipping the test.");


    }

}
