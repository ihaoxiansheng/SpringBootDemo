package com.hao.springbootdemo.test.task;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * java 节流(throttle)
 *
 * @author xu.liang
 * @since 2022/10/19 11:01
 */
public class ThrottleTask2 {
    /**
     * 节流实现关键类：
     */
    private ScheduledExecutorService timer;
    private Long delay;
    private Runnable runnable;
    private boolean needWait=false;

    /**
     * 有参构造函数
     * @param runnable 要启动的定时任务
     * @param delay 延迟时间
     */
    public void ThrottleTask(Runnable runnable, Long delay) {
        this.runnable = runnable;
        this.delay = delay;
        this.timer = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * build 创建对象，相当于 ThrottleTask task = new ThrottleTask();
     * @param runnable 要执行的节流任务
     * @param delay 延迟时间
     * @return ThrottleTask 对象
     */
    public static ThrottleTask build(Runnable runnable, Long delay){
        return new ThrottleTask(runnable, delay);
    }

    public void taskRun(){
        //如果 needWait 为 false,结果取反，表达式为 true。执行 if 语句
        if(!needWait){
            //设置为 true,这样下次就不会再执行
            needWait=true;
            //执行节流方法
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //执行完成,设置为 false,让下次操作再进入 if 语句中
                    needWait=false;
                    //开启多线程执行 run() 方法
                    runnable.run();
                }
            }, delay, TimeUnit.MILLISECONDS);
        }
    }
}
