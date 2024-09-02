package com.hao.test.year.demo2024.demo4;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author xu.liang
 * @since 2024/4/23 19:10
 */
public class Demo3Test {

    public static void main(String[] args) {

        System.out.println("hello world");

        String simpleName = Demo3Test.class.getSimpleName();
        System.out.println("simpleName = " + simpleName);

        System.out.println();

        // 常规ULID
        Ulid ulid = UlidCreator.getUlid();
        System.out.println("ulid = " + ulid);
        long ulidTime = ulid.getTime();
        System.out.println("ulidTime = " + ulidTime);
        byte[] ulidRandom = ulid.getRandom();
        System.out.println("ulidRandom = " + Arrays.toString(ulidRandom));
        Instant ulidInstant = ulid.getInstant();
        System.out.println("ulidInstant = " + ulidInstant);

        UUID uuid = ulid.toUuid();
        System.out.println("uuid = " + uuid);

        // 单调排序ULID
        Ulid monotonicUlid = UlidCreator.getMonotonicUlid();
        System.out.println("monotonicUlid = " + monotonicUlid);

        Ulid monotonicUlid1 = UlidCreator.getMonotonicUlid(1670837655000L);
        System.out.println("monotonicUlid1 = " + monotonicUlid1);
        Ulid monotonicUlid2 = UlidCreator.getMonotonicUlid(1670837655000L);
        System.out.println("monotonicUlid2 = " + monotonicUlid2);
        // 可以看出 monotonicUlid1 最后一位比 monotonicUlid2 小 1
        // 单调排序ULID的时钟部分是固定的，所以单调排序ULID是单调排序的，但是不能保证时钟的绝对顺序，所以单调排序ULID不能保证绝对顺序。
        System.out.println("monotonicUlid1.compareTo(monotonicUlid2) = " + monotonicUlid1.compareTo(monotonicUlid2));

        try {
            Assert.isTrue(false, "测试try断言是否能捕捉到");
        } catch (Exception e) {
            // 能捕捉到异常，打印该行
            System.out.println("捕捉到断言报错>>>>>>>>>>>");
            // throw new RuntimeException(e);
        }
        System.out.println("未捕捉到断言报错>>>>>>>>>>>");

        JSONObject jsonObject = JSON.parseObject(null);
        System.out.println("jsonObject = " + jsonObject);

    }
}
