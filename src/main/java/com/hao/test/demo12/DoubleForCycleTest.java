package com.hao.test.demo12;

import com.hao.entity.User1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 双重for循环测试<p>
 * <a href="https://blog.csdn.net/xx1433251330/article/details/127701789">...</a>
 *
 * @author xu.liang
 * @since 2023/12/26 09:27
 */
public class DoubleForCycleTest {

    public static void main(String[] args) {
        List<User1> list1 = new ArrayList<>();
        List<User1> list2 = new ArrayList<>();
        for (int i = 0; i < 10_0000; i++) {
            list1.add(User1.builder().id(String.valueOf(i)).build());
            list2.add(User1.builder().id(String.valueOf(i)).build());
        }
        long start = System.currentTimeMillis();
        test1(list1, list2);
        System.out.println("for循环耗时：" + (System.currentTimeMillis() - start));
    }

    /**
     * 双重for循环<p>
     * 1w 耗时：343
     * <p>
     * 10w 耗时：64285
     */
    private static void test1(List<User1> list1, List<User1> list2) {
        for (User1 before : list1) {
            for (User1 after : list2) {
                if (before.getId().equals(after.getId())) {
                    // 业务逻辑
                    break;
                }
            }
        }
    }

    /**
     * 将一个for循环转为map<p>
     * 1w 耗时：88
     * <p>
     * 10w 耗时：95
     */
    private static void test2(List<User1> list1, List<User1> list2) {
        Map<String, User1> baseMap =
                list2.stream().collect(Collectors.toMap(User1::getId, Function.identity()));
        for (User1 before : list1) {
            User1 after = baseMap.get(before.getId());
            System.out.println("after = " + after);

        }
    }


}
