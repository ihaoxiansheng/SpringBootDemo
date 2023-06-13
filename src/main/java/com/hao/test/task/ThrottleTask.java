package com.hao.test.task;

import java.util.Timer;
import java.util.TimerTask;

/**
 * java 节流(throttle)
 * <p>
 * 那么定时器 Timer 和 ScheduledThreadPoolExecutor 解决方案之间的主要区别是什么，我总结了三点...
 * 定时器对系统时钟的变化敏感；ScheduledThreadPoolExecutor并不会。
 * 定时器只有一个执行线程；ScheduledThreadPoolExecutor可以配置任意数量的线程。
 * TimerTask中抛出的运行时异常会杀死线程，因此后续的计划任务不会继续运行；使用ScheduledThreadExecutor–当前任务将被取消，但其余任务将继续运行
 *
 * @author xu.liang
 * @since 2022/10/19 11:01
 */
// @SuppressWarnings("all")
public class ThrottleTask {
    /**
     * 节流实现关键类
     */
    private Timer timer;
    private Long delay;
    private Runnable runnable;
    private boolean needWait = false;

    /**
     * 有参构造函数
     *
     * @param runnable 要启动的定时任务
     * @param delay    延迟时间
     */
    public ThrottleTask(Runnable runnable, Long delay) {
        this.runnable = runnable;
        this.delay = delay;
        this.timer = new Timer();
    }

    /**
     * build 创建对象，相当于 ThrottleTask task = new ThrottleTask();
     *
     * @param runnable 要执行的节流任务
     * @param delay    延迟时间
     * @return ThrottleTask 对象
     */
    public static ThrottleTask build(Runnable runnable, Long delay) {
        return new ThrottleTask(runnable, delay);
    }

    public void taskRun() {
        //如果 needWait 为 false,结果取反，表达式为 true。执行 if 语句
        if (!needWait) {
            //设置为 true,这样下次就不会再执行
            needWait = true;
            //执行节流方法
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //执行完成,设置为 false,让下次操作再进入 if 语句中
                    needWait = false;
                    //开启多线程执行 run() 方法
                    runnable.run();
                }
            }, delay);
        }
    }

    // 测试1
    public static void main(String[] args) {
        //创建节流要执行的对象,并把要执行的任务传入进去
        ThrottleTask task = ThrottleTask.build(new Runnable() {
            @Override
            public void run() {
                System.out.println("节流任务执行：do task: " + System.currentTimeMillis());
            }
        }, 1000L);
        //while一直执行,模拟前端用户一直请求后端
        while (true) {
            System.out.println("前端请求后端：call task: " + System.currentTimeMillis());
            task.taskRun();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
