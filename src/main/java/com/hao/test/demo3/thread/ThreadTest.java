package com.hao.test.demo3.thread;

/**
 * @author xu.liang
 * @since 2023/3/9 11:33
 */
public class ThreadTest extends Thread {
    @Override
    public void run() {
        // super.run();
        System.out.println("执行run");
    }


    public static void main(String[] args) {
        new ThreadTest().run();
        //
        // Thread thread = Thread.currentThread();
        // System.out.println("thread = " + thread.getName());
    }

}
