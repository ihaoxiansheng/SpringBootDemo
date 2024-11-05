package com.hao.test.year.demo2024.demo9;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread.sleep(0) 测试<p>
 * for循环中的下标为int类型会输出：
 * <pre>
 * Thread-0执行结束!
 * num = 2000000000
 * Thread-1执行结束!
 * </pre>
 * <pre>
 * for循环中的下标为long类型会输出：
 * num = 59586824
 * Thread-0执行结束!
 * Thread-1执行结束!
 * </pre>
 * 详细见链接：<a href="https://zhuanlan.zhihu.com/p/563860490">...</a>
 * <p>彩蛋：问题在JDK10或以上已经优化了
 *
 * @author xu.liang
 * @since 2024/9/14 13:55
 */
public class ThreadSleepZeroTest {

    public static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 1000000000; i++) {
                num.getAndAdd(1);

                // prevent gc，for循环下标类型为int下面代码原理：
                // 1.正在执行 native 函数的线程可以看作"已经进入了 safepoint "。
                // 2.由于 sleep 方法是 native 的，所以调用 sleep 方法的线程会进入 safepoint。
                if (i % 1000 == 0) {
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
            System.out.println(Thread.currentThread().getName() + "执行结束!");
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("num = " + num);
    }
}
