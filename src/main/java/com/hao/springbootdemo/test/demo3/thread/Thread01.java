package com.hao.springbootdemo.test.demo3.thread;

/**
 * 为什么要重写run()方法?
 * 因为run()是用来封装被线程执行的代码
 * run()和start()方法的区别？
 * run()：封装线程启动的代码，直接调用，相当于普通方法的调用
 * start()：启动线程，然后由JVM调用此线程的run()方法
 *
 * @author xu.liang
 * @since 2023/3/9 13:54
 */
public class Thread01 {

    public static void main(String[] args) {
        MyThread01 mt1 = new MyThread01();
        MyThread01 mt2 = new MyThread01();
        // mt1.run();
        // mt2.run();
        // Calls to 'run()' should probably be replaced with 'start()'
        long t1 = System.nanoTime();
        mt1.start();
        mt2.start();
        long t2 = System.nanoTime();

        System.out.printf("serial: %.2fs", (t2 - t1) * 1e-9);
    }
}

class MyThread01 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }

}
