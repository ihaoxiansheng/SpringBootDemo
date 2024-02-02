package com.hao.test.year.demo2023.demo3.thread;

/**
 * 定义一个类MyRunnable实现Runnable接口
 * 在MyRunnable类中重写run()方法
 * 创建MyRunnable对象
 * 创建Thread对象，把MyRunnable对象作为构造方法的参数
 * 启动线程
 * <p>
 * 相比继承Thread类，实现Runnable接口的好处:
 * 避免了Java单继承的局限性
 * 适合多个相同程序的代码去处理同一个资源的情况，把线程、程序的代码和数据 有效分离、较好的体现了面向对象的思想
 *
 * @author xu.liang
 * @since 2023/3/9 14:08
 */
public class Thread04 {

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        // public Thread(Runnable target)
        // Thread t1 = new Thread(myRunnable);
        // Thread t2 = new Thread(myRunnable);
        //  public Thread(Runnable target, String name)
        Thread t1 = new Thread(new MyRunnable(), "高铁");// 匿名内部类使用
        Thread t2 = new Thread(new MyRunnable(), "飞机");

        t1.start();
        t2.start();

    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }


}
