package com.hao.test.demo6;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xu.liang
 * @since 2023/6/25 10:06
 */
@Slf4j
public class LockTimeOut {

    private final RedissonClient redissionClient;

    private static final Lock lock = new ReentrantLock();

    private static String aa = "";

    public LockTimeOut(RedissonClient redissionClient) {
        this.redissionClient = redissionClient;
    }

    public static String getInstance() {
        lock.lock();
        try {
            if (aa == null) {
                aa = "hello world";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("初始化CTGRedisManager时，锁获取失败");
        } finally {
            lock.unlock();
        }
        return aa;
    }

    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        RLock lock = null;
        try {
            lock = redissionClient.getLock(joinPoint.getSignature().toString());
            if (lock.tryLock(0, 2, TimeUnit.MINUTES)) {
                return joinPoint.proceed();
            }
            throw new RuntimeException("操作正在进行中，请勿重复操作");
        } finally {
            if (lock != null) {
                if (lock.isLocked()) {
                    lock.unlock();
                }
            }
        }

    }


}
