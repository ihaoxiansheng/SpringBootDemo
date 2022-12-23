package com.hao.springbootdemo.test.task;

import java.util.Timer;
import java.util.TimerTask;

/**
 * java 防抖(debounce)
 *
 * @author xu.liang
 * @since 2022/10/19 10:58
 */
@SuppressWarnings("all")
public class DebounceTask {
    /**
     * 防抖实现关键类
     */
    private Timer timer;
    /**
     * 防抖时间:根据业务评估
     */
    private Long delay;
    /**
     * 开启线程执行任务
     */
    private Runnable runnable;

    public DebounceTask(Runnable runnable, Long delay) {
        this.runnable = runnable;
        this.delay = delay;
    }

    /**
     * @param runnable 要执行的任务
     * @param delay    执行时间
     * @return 初始化 DebounceTask 对象
     */
    public static DebounceTask build(Runnable runnable, Long delay) {
        return new DebounceTask(runnable, delay);
    }

    //Timer类执行:cancel()-->取消操作；schedule()-->执行操作
    public void timerRun() {
        //如果有任务,则取消不执行(防抖实现的关键)
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //把 timer 设置为空,这样下次判断它就不会执行了
                timer = null;
                //执行 runnable 中的 run()方法
                runnable.run();
            }
        }, delay);
    }


    // 测试1
    public static void main(String[] args){
        //构建对象，1000L: 1秒执行-->1秒内没有请求，在执行防抖操作
        DebounceTask task = DebounceTask.build(new Runnable() {
            @Override
            public void run() {
                System.out.println("防抖操作执行了:do task: "+System.currentTimeMillis());
            }
        },1000L);
        long delay = 100;
        while (true){
            System.out.println("请求执行:call task: "+System.currentTimeMillis());
            task.timerRun();
            try {
                //休眠1毫秒在请求
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 测试2
    // public static void main(String[] args){
    //     //构建对象，1000L:1秒执行
    //     DebounceTask task = DebounceTask.build(new Runnable() {
    //         @Override
    //         public void run() {
    //             System.out.println("防抖操作执行了:do task: "+System.currentTimeMillis());
    //         }
    //     },1000L);
    //     long delay = 100;
    //     long douDelay = 0;
    //     while (true){
    //         System.out.println("请求执行:call task: "+System.currentTimeMillis());
    //         task.timerRun();
    //         douDelay = douDelay+100;
    //         try {
    //             //如果请求执行了两秒,我们让他先休息两秒,在接着请求
    //             if (douDelay == 2000){
    //                 Thread.sleep(douDelay);
    //             }
    //             Thread.sleep(delay);
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }

    // 测试3 简易版
    // public static void main(String[] args){
    //     //要执行的任务,因为 Runnable 是接口,所以 new 对象的时候要实现它的 run方法
    //     Runnable runnable =  new Runnable() {
    //         @Override
    //         public void run() {
    //             //执行打印,真实开发中,是这些我们的业务代码。
    //             System.out.println("防抖操作执行了:do task: "+System.currentTimeMillis());
    //         }
    //     };
    //
    //     //runnable:要执行的任务,通过参数传递进去。1000L:1秒执行内没有请求,就执行一次防抖操作
    //     DebounceTask task = DebounceTask.build(runnable,1000L);
    //     //请求持续时间
    //     long delay = 100;
    //     //休眠时间,为了让防抖任务执行
    //     long douDelay = 0;
    //
    //     //while 死循环,请求一直执行
    //     while (true){
    //         System.out.println("请求执行:call task: "+System.currentTimeMillis());
    //         //调用 DebounceTask 防抖类中的 timerRun() 方法, 执行防抖任务
    //         task.timerRun();
    //         douDelay = douDelay+100;
    //         try {
    //             //如果请求执行了两秒,我们让他先休息两秒,在接着请求
    //             if (douDelay == 2000){
    //                 Thread.sleep(douDelay);
    //             }
    //             Thread.sleep(delay);
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }


}
