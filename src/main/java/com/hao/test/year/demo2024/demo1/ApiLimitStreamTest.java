package com.hao.test.year.demo2024.demo1;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xu.liang
 * @since 2024/1/11 09:20
 */
@Slf4j
public class ApiLimitStreamTest {

    public static void main(String[] args) {
//        acquireTest();
        acquireSmoothly();
    }

    /**
     * 令牌算法，固定产生令牌
     */
    public static void acquireTest() {
        // 每秒固定生成5个令牌
        RateLimiter rateLimiter = RateLimiter.create(2);
        for (int i = 0; i < 10; i++) {
            double time = rateLimiter.acquire();
            log.info("等待时间：{}s", time);
            System.out.println("i = " + i);
        }
    }

    /**
     * 令牌算法，同时产生多个令牌
     */
    public static void acquireSmoothly() {
        RateLimiter rateLimiter = RateLimiter.create(5, 3, TimeUnit.SECONDS);
        long startTimeStamp = System.currentTimeMillis();
        for (int i = 0; i < 15; i++) {
            double time = rateLimiter.acquire();
            log.info("等待时间:{}s, 总时间:{}ms", time, System.currentTimeMillis() - startTimeStamp);
            System.out.println("i = " + i);
        }
    }

}

class FixedWindowRateLimiter {
    Logger logger = LoggerFactory.getLogger(FixedWindowRateLimiter.class);
    // 时间窗口大小，单位毫秒
    long windowSize;
    // 允许通过的请求数
    int maxRequestCount;
    // 当前窗口通过的请求数
    AtomicInteger counter = new AtomicInteger(0);
    // 窗口右边界
    long windowBorder;

    public FixedWindowRateLimiter(long windowSize, int maxRequestCount) {
        this.windowSize = windowSize;
        this.maxRequestCount = maxRequestCount;
        this.windowBorder = System.currentTimeMillis() + windowSize;
    }

    public synchronized boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();
        if (windowBorder < currentTime) {
            logger.info("window reset");
            do {
                windowBorder += windowSize;
            }
            while (windowBorder < currentTime);
            counter = new AtomicInteger(0);
        }
        if (counter.intValue() < maxRequestCount) {
            counter.incrementAndGet();
            logger.info("tryAcquire success");
            return true;
        } else {
            logger.info("tryAcquire fail");
            return false;
        }
    }
}

class SlidingWindowRateLimiter {
    Logger logger = LoggerFactory.getLogger(SlidingWindowRateLimiter.class);
    //时间窗口大小，单位毫秒
    long windowSize;
    // 分片窗口数
    int shardNum;
    // 允许通过的请求数
    int maxRequestCount;
    // 各个窗口内请求计数
    int[] shardRequestCount;
    // 请求总数
    int totalCount;
    // 当前窗口下标
    int shardId;
    // 每个小窗口大小，毫秒
    long tinyWindowSize;
    // 窗口右边界
    long windowBorder;

    public SlidingWindowRateLimiter(long windowSize, int shardNum, int maxRequestCount) {
        this.windowSize = windowSize;
        this.shardNum = shardNum;
        this.maxRequestCount = maxRequestCount;
        this.shardRequestCount = new int[shardNum];
        this.tinyWindowSize = windowSize / shardNum;
        this.windowBorder = System.currentTimeMillis();
    }

    public synchronized boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();
        if (windowBorder < currentTime) {
            logger.info("window reset");
            do {
                shardId = (++shardId) % shardNum;
                totalCount -= shardRequestCount[shardId];
                shardRequestCount[shardId] = 0;
                windowBorder += tinyWindowSize;
            } while (windowBorder < currentTime);
        }
        if (totalCount < maxRequestCount) {
            logger.info("tryAcquire success:{}", shardId);
            shardRequestCount[shardId]++;
            totalCount++;
            return true;
        } else {
            logger.info("tryAcquire fail");
            return false;
        }
    }
}

class LeakyBucketRateLimiter {
    Logger logger = LoggerFactory.getLogger(LeakyBucketRateLimiter.class);
    //桶的容量
    int capacity;
    // 桶中现存水量
    AtomicInteger water = new AtomicInteger();
    // 开始漏水时间
    long leakTimestamp;
    // 水流出的速率，即每秒允许通过的请求数
    int leakRate;

    public LeakyBucketRateLimiter(int capacity, int leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
    }

    public synchronized boolean tryAcquire() {
        //桶中没有水， 重新开始计算


        if (water.get() == 0) {
            logger.info("start leaking");
            leakTimestamp = System.currentTimeMillis();
            water.incrementAndGet();
            return water.get() < capacity;
        }
        //先漏水，计算剩余水量
        long currentTime = System.currentTimeMillis();
        int leakedWater = (int) ((currentTime - leakTimestamp) / 1000 * leakRate);
        logger.info("lastTime:{}, currentTime:{}. LeakedWater:{}", leakTimestamp, currentTime, leakedWater);
        // 可能时间不足，则先不漏水
        if (leakedWater != 0) {
            int leftWater = water.get() - leakedWater;
            // 可能水已漏光。设为0
            water.set(Math.max(0, leftWater));
            leakTimestamp = System.currentTimeMillis();
        }
        logger.info("剩余容量:{}", capacity - water.get());
        if (water.get() < capacity) {
            logger.info("tryAcquire sucess");
            water.incrementAndGet();
            return true;
        } else {
            logger.info("tryAcquire fail");
            return false;
        }
    }
}

