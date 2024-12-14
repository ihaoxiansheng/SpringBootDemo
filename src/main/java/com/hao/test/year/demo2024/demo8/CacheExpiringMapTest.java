package com.hao.test.year.demo2024.demo8;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author xu.liang
 * @since 2024/8/1 15:43
 */
@Slf4j
public class CacheExpiringMapTest {

    private static final ExpiringMap<String, List<String>> CACHE_EXPIRINGMAP = ExpiringMap.builder()
            // Map存储的最大值，类似队列，容量固定，当操作map容量超出限制时，最开始的元素就会依次过期，只保留最新的
            .maxSize(1000)
            // 过期时间，可设置时、分、秒
            .expiration(24, TimeUnit.HOURS)
            // 过期策略，CREATED表示基于创建时间过期，ACCESSED表示在过期时间内重新put值的话，过期时间会清理，重新计算
            .expirationPolicy(ExpirationPolicy.CREATED)
            .variableExpiration().build();

    private static final AsyncCache<String, List<String>> CACHE_CAFFEINE = Caffeine.newBuilder().initialCapacity(10).maximumSize(20).expireAfterWrite(5, TimeUnit.MINUTES).recordStats().buildAsync();


    public static List<String> test() {
        List<String> list = CACHE_EXPIRINGMAP.get("list");
        CompletableFuture<List<String>> cacheIfPresent = CACHE_CAFFEINE.getIfPresent("list");
        if (cacheIfPresent != null) {
            try {
                log.info("Caffeine，从缓存中获取：{}", cacheIfPresent.get());
            } catch (Exception e) {
                log.error("从缓存中获取数据异常：{}", e.getMessage());
                log.error(ExceptionUtils.getStackTrace(e));
            }
        }
        if (CollectionUtils.isNotEmpty(list)) {
            log.info("ExpiringMap，从缓存中获取：{}", list);
            return list;
        }
        List<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(String.valueOf(i));
        }
        log.info("从数据库中获取：{}", arrayList);
        CACHE_EXPIRINGMAP.put("list", arrayList);
        CACHE_CAFFEINE.put("list", CompletableFuture.completedFuture(arrayList));
        // CACHE_EXPIRINGMAP.clear();
        // CACHE_CAFFEINE.synchronous().invalidate("list");

        return arrayList;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            test();
        }
    }
}
