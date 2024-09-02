package com.hao.test.year.demo2024.demo9;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * RateLimiter优雅限流 Beta
 *
 * @author xu.liang
 * @since 2024/9/2 09:54
 */
@Slf4j
public class AdvancedRateLimiterDemo {

    // 存储每个用户的API请求RateLimiter
    private static final Map<String, RateLimiter> apiRateLimiters = new HashMap<>();

    // 存储每个用户的登录尝试RateLimiter
    private static final Map<String, RateLimiter> loginRateLimiters = new HashMap<>();

    // 用于创建API请求RateLimiter的工厂方法
    public static RateLimiter createApiRateLimiter(double permitsPerSecond) {
        return RateLimiter.create(permitsPerSecond); // 每秒生成的令牌数
    }

    // 用于创建登录尝试RateLimiter的工厂方法
    public static RateLimiter createLoginRateLimiter(double permitsPerSecond) {
        return RateLimiter.create(permitsPerSecond);
    }

    // 获取或创建用户的API请求RateLimiter
    public static RateLimiter getApiRateLimiter(String userId) {
        return apiRateLimiters.computeIfAbsent(userId, k -> createApiRateLimiter(10.0)); // 每秒最多10个API请求
    }

    // 获取或创建用户的登录尝试RateLimiter
    public static RateLimiter getLoginRateLimiter(String userId) {
        return loginRateLimiters.computeIfAbsent(userId, k -> createLoginRateLimiter(1.0)); // 每秒最多1次登录尝试
    }

    // 模拟API请求
    public static boolean tryApiRequest(String userId) {
        RateLimiter rateLimiter = getApiRateLimiter(userId);
        if (!rateLimiter.tryAcquire()) {
            System.out.println("API请求过于频繁，请稍后再试。用户ID: " + userId);
            return false;
        }
        // 这里可以执行实际的API请求逻辑
        System.out.println("API请求成功处理。用户ID: " + userId);
        return true;
    }

    // 模拟用户登录尝试
    public static boolean tryLoginAttempt(String userId) {
        RateLimiter rateLimiter = getLoginRateLimiter(userId);
        if (!rateLimiter.tryAcquire()) {
            System.out.println("登录尝试过于频繁，请稍后再试。用户ID: " + userId);
            return false;
        }
        // 这里可以执行实际的登录验证逻辑
        System.out.println("登录尝试成功处理。用户ID: " + userId);
        return true;
    }

    public static void main(String[] args) {
        // 模拟同一用户连续发送多个API请求
        String apiUserId = "api-user-123";
        for (int i = 0; i < 15; i++) {
            new Thread(() -> tryApiRequest(apiUserId)).start();
            try {
                TimeUnit.MILLISECONDS.sleep(100); // 每隔200毫秒发送一个请求
            } catch (InterruptedException e) {
                log.error("API请求异常", e);
            }
        }

        // 模拟同一用户连续尝试登录
        String loginUserId = "login-user-456";
        for (int i = 0; i < 10; i++) {
            new Thread(() -> tryLoginAttempt(loginUserId)).start();
            try {
                TimeUnit.MILLISECONDS.sleep(200); // 每隔200毫秒尝试一次登录
            } catch (InterruptedException e) {
                log.error("登录尝试异常", e);
            }
        }
    }
}
