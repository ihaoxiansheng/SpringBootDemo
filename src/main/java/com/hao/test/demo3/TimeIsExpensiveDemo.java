package com.hao.test.demo3;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Java防抖<p>
 * 在一定的间隔内多次调用一个函数，只有最后一次才生效，例如，我设定的间隔为1秒，那么只要我在1秒内触发了第二次，那么就重新计时
 *
 * @author xu.liang
 * @since 2023/3/22 09:03
 */
public class TimeIsExpensiveDemo {

    public static void main(String[] args) {
        long total = 0;
        for (int i = 1; i <= 10; i++) {
            long startTime = System.nanoTime();
            // 真正执行的函数，其他都是计时用的
            ruben();
            try {
                Thread.sleep(500);
                long endTime = System.nanoTime();
                total += endTime - startTime;
                System.out.println("执行了" + total / (1000.0 * 1000.0) + "ms");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 初始化任务
     */
    public static TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
        }
    };

    /**
     * 执行的函数
     */
    public static void ruben() {
        // 每次进来都清零
        timerTask.cancel();
        // 然后创建一个新的任务
        timerTask = new TimerTask() {
            public void run() {
                System.out.println("最后一次循环后才执行我");
            }
        };
        // 执行任务
        new Timer().schedule(timerTask, 1000);
    }

}
