package com.hao.test.jvm;

import com.sun.management.OperatingSystemMXBean;
import com.sun.management.ThreadMXBean;

import java.lang.management.*;
import java.util.List;

/**
 * 获取JVM参数
 *
 * @author xu.liang
 * @since 2022/10/19 13:56
 */
public class Demo02 {
    public static void main(String[] args) {
        MemoryMXBean runtimeMxBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = runtimeMxBean.getHeapMemoryUsage();
        System.out.println("INIT HEAP: " + usage.getInit());
        System.out.println("MAX HEAP: " + usage.getMax());
        System.out.println("USE HEAP: " + usage.getUsed());
        System.out.println("\nFull Information:");
        System.out.println("Heap Memory Usage: " + runtimeMxBean.getHeapMemoryUsage());
        System.out.println("Non-Heap Memory Usage: " + runtimeMxBean.getNonHeapMemoryUsage());

        List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
        System.out.println("===================java options=============== ");
        System.out.println("inputArguments = " + inputArguments);

        System.out.println("=======================通过java来获取相关系统状态============================ ");
        // Java 虚拟机中的内存总量,以字节为单位
        int i = (int) Runtime.getRuntime().totalMemory() / 1024;
        System.out.println("总的内存量 i is: " + i);
        // Java 虚拟机中的空闲内存量
        int j = (int) Runtime.getRuntime().freeMemory() / 1024;
        System.out.println("空闲内存量 j is: " + j);
        System.out.println("最大内存量 is: " + Runtime.getRuntime().maxMemory() / 1024);

        System.out.println("=======================OperatingSystemMXBean============================ ");
        OperatingSystemMXBean osm = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        System.out.println(osm.getFreeSwapSpaceSize() / 1024);
        System.out.println(osm.getFreePhysicalMemorySize() / 1024);
        System.out.println(osm.getTotalPhysicalMemorySize() / 1024);

        // 获取操作系统相关信息
        System.out.println("osm.getArch(): " + osm.getArch());
        System.out.println("osm.getAvailableProcessors(): " + osm.getAvailableProcessors());
        System.out.println("osm.getCommittedVirtualMemorySize() " + osm.getCommittedVirtualMemorySize());
        System.out.println("osm.getName():" + osm.getName());
        System.out.println("osm.getProcessCpuTime():" + osm.getProcessCpuTime());
        System.out.println("osm.getVersion(): " + osm.getVersion());
        // 获取整个虚拟机内存使用情况
        System.out.println("=======================MemoryMXBean============================ ");
        MemoryMXBean mm = (MemoryMXBean) ManagementFactory.getMemoryMXBean();
        System.out.println("getHeapMemoryUsage: " + mm.getHeapMemoryUsage());
        System.out.println("getNonHeapMemoryUsage: " + mm.getNonHeapMemoryUsage());
        // 获取各个线程的各种状态，CPU 占用情况，以及整个系统中的线程状况
        System.out.println("=======================ThreadMXBean============================ ");
        ThreadMXBean tm = (ThreadMXBean) ManagementFactory.getThreadMXBean();
        System.out.println("getThreadCount: " + tm.getThreadCount());
        System.out.println("getPeakThreadCount: " + tm.getPeakThreadCount());
        System.out.println("getCurrentThreadCpuTime: " + tm.getCurrentThreadCpuTime());
        System.out.println("getDaemonThreadCount: " + tm.getDaemonThreadCount());
        System.out.println("getCurrentThreadUserTime: " + tm.getCurrentThreadUserTime());

        // 当前编译器情况
        System.out.println("=======================CompilationMXBean============================ ");
        CompilationMXBean gm = (CompilationMXBean) ManagementFactory.getCompilationMXBean();
        System.out.println("getName: " + gm.getName());
        System.out.println("getTotalCompilationTime: " + gm.getTotalCompilationTime());

        // 获取多个内存池的使用情况
        System.out.println("=======================MemoryPoolMXBean============================ ");
        List<MemoryPoolMXBean> mpmList = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean mpm : mpmList) {
            System.out.println("getUsage: " + mpm.getUsage());
            System.out.println("getMemoryManagerNames: " + mpm.getMemoryManagerNames().toString());
        }
        // 获取GC的次数以及花费时间之类的信息
        System.out.println("=======================MemoryPoolMXBean============================ ");
        List<GarbageCollectorMXBean> gcmList = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcm : gcmList) {
            System.out.println("getName: " + gcm.getName());
            System.out.println("getMemoryPoolNames: " + gcm.getMemoryPoolNames());
        }
        // 获取运行时信息
        System.out.println("=======================RuntimeMXBean============================ ");
        RuntimeMXBean rmb = (RuntimeMXBean) ManagementFactory.getRuntimeMXBean();
        System.out.println("getClassPath: " + rmb.getClassPath());
        System.out.println("getLibraryPath: " + rmb.getLibraryPath());
        System.out.println("getVmVersion: " + rmb.getVmVersion());


    }
}
