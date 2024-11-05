package com.hao.test.year.demo2024.demo9;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * java 从一亿个数据中取出前十个最大值
 * <p>最小堆（Min-Heap）：使用最小堆的思想是维护一个大小为10的堆，遍历数据时，堆中始终保持当前最大的前10个元素。遍历完所有数据后，堆中的元素就是前10个最大值。
 *
 * @author xu.liang
 * @since 2024/9/14 14:13
 */
public class Top10MaxValuesTest {

    public static void main(String[] args) {
        // 创建一个随机数生成器，用于生成一亿个随机数
        Random random = new Random();

        // 定义一个最小堆，堆的大小为10
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(10);

        // 模拟一亿个数据
        for (int i = 0; i < 100000000; i++) {
            int num = random.nextInt(Integer.MAX_VALUE);  // 生成随机数

            // 当堆的大小小于10时，直接加入
            if (minHeap.size() < 10) {
                minHeap.offer(num);
            } else {
                // 如果堆满了，比较当前数和堆顶（最小值）
                if (num > minHeap.peek()) {
                    minHeap.poll();   // 移除堆顶元素
                    minHeap.offer(num);  // 将当前数加入堆
                }
            }
        }

        // 打印出最小堆中的前10个最大值
        System.out.println("前10个最大值为：");
        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.poll());
        }
    }
}
