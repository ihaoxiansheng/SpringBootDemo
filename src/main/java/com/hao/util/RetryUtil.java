package com.hao.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * 方法重试工具类
 * 调用：RetryUtil.retry(3,() -> 方法名)
 *
 * @author xu.liang
 * @since 2024/9/13 11:27
 */
@Slf4j
public class RetryUtil {

    /**
     * @param retryCount   重试次数
     * @param supplier     调用方法
     * @param defaultValue 全部异常时，返回的值
     * @param <T>          对象类型
     * @return 返回的对象
     */
    public static <T> T retry(int retryCount, Supplier<T> supplier, Supplier<T> defaultValue) {
        try {
            return retry(retryCount, supplier);
        } catch (Exception e) {
            log.error("调用失败，返回默认值！", e);
            return defaultValue == null ? null : defaultValue.get();
        }
    }

    /***
     * 无返回值的
     * @param retryCount 重试次数
     * @param runnable
     */
    public static void retry(int retryCount, Runnable runnable) {
        RetryUtil.retry(retryCount, () -> {
            runnable.run();
            return null;
        });
    }

    public static <T> T retry(int retryCount, Supplier<T> supplier) {
        return retry(retryCount > 0, retryCount, 0, supplier);
    }

    /**
     * @param retryFlag   是否重试
     * @param retryCount  重试次数
     * @param tryInterval 重试间隔
     * @param supplier    方法
     * @param <T>         对象类型
     * @return 返回的对象
     */
    public static <T> T retry(boolean retryFlag, int retryCount, int tryInterval, Supplier<T> supplier) {
        if (retryFlag) {
            return retry(retryCount, tryInterval, supplier);
        } else {
            return supplier.get();
        }
    }

    /**
     * @param retryCount  重试次数
     * @param tryInterval 重试间隔
     * @param supplier    调用方法
     * @param <T>         对象类型
     * @return 返回的对象
     */
    @SneakyThrows
    public static <T> T retry(int retryCount, int tryInterval, Supplier<T> supplier) {
        Exception temp = null;
        for (int i = 0; i < retryCount + 1; i++) {
            try {
                log.info("第{}次调用!", i + 1);
                return supplier.get();
            } catch (Exception e) {
                log.error("第" + (i + 1) + "次调用失败！", e);
                temp = e;
                if (tryInterval > 0) {
                    Thread.sleep(tryInterval * 1000L);
                }
            }
        }
        throw new RuntimeException("调用失败！", temp);
    }

}
