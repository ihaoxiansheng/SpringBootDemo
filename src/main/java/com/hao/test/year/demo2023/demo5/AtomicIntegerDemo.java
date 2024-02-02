package com.hao.test.year.demo2023.demo5;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger-用于解决count++
 * <p>
 * 因为count++操作不是原子的，可能第一个线程拿到的count=0将要进行++操作的时候，被其他线程抢占了CPU资源，
 * 第二个线程此时读取的还是count=0，那么这样就会造成问题，而AtomicInteger它内部使用CAS算法保证了并发安全问题。
 *
 * @author xu.liang
 * @since 2023/5/24 16:30
 */
public class AtomicIntegerDemo {

    private static int count = 0;
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 1000; j++) {
                    count++;
                    ATOMIC_INTEGER.incrementAndGet(); // 内部CAS进行自增
                }
            });
            thread.start();
            threads[i] = thread;
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println("count结果: " + count);
        System.out.println("atomic结果: " + ATOMIC_INTEGER.get());
    }

}
