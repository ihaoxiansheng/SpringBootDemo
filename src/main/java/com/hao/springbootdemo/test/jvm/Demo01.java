package com.hao.springbootdemo.test.jvm;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import com.sun.management.OperatingSystemMXBean;
import sun.management.ManagementFactoryHelper;

import java.io.File;
import java.lang.management.*;

/**
 * @author xu.liang
 * @since 2022/10/19 13:46
 */
public class Demo01 {
    public static void main(String[] args) {
        // 首先获取jvm相关信息，包含jvm的名称、版本号、启动时间、运行时间、环境变量、进程id等等
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        // jvmName
        System.out.printf("jvmName: %s %n", runtimeMxBean.getVmName());
        // jvmVersion
        System.out.printf("jvmVersion: %s %n", runtimeMxBean.getVmVersion());
        // jvmVendor
        System.out.printf("jvmVendor: %s %n", runtimeMxBean.getVmVendor());
        // startTime 使用hutool中DateUtil进行转换
        long startTime = runtimeMxBean.getStartTime();
        System.out.println("startTime = " + startTime);
        System.out.printf("startTime: %s %n", DateUtil.date(startTime));
        // updateTime
        long uptime = runtimeMxBean.getUptime();
        System.out.printf("updateTime: %s %n", DateUtil.formatBetween(uptime, BetweenFormatter.Level.SECOND));
        // classPath
        System.out.printf("classPath: %s %n", runtimeMxBean.getClassPath());
        // systemProperties
        System.out.printf("jvmName: %s %n", runtimeMxBean.getSystemProperties());
        // bootClassPath
        System.out.printf("bootClassPath: %s %n", runtimeMxBean.getBootClassPath());
        // processId
        System.out.printf("processId: %s %n", runtimeMxBean.getName().split("@")[0]);

        // 获取JVM内存相关信息，例如堆内存
        MemoryMXBean memoryMxBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMxBean.getHeapMemoryUsage();
        // heapMemoryUsed
        System.out.printf("heapMemoryUsed: %d MB %n", heapMemoryUsage.getUsed() / 1024 / 1024);
        // heapMemoryMax
        System.out.printf("heapMemoryMax: %d MB %n", heapMemoryUsage.getMax() / 1024 / 1024);
        // heapMemoryCommitted
        System.out.printf("heapMemoryCommitted: %d MB %n", heapMemoryUsage.getCommitted() / 1024 / 1024);
        MemoryUsage nonHeapMemoryUsage = memoryMxBean.getNonHeapMemoryUsage();
        // nonHeapMemoryUsed
        System.out.printf("nonHeapMemoryUsed: %d MB %n", nonHeapMemoryUsage.getUsed() / 1024 / 1024);
        // nonHeapMemoryMax
        System.out.printf("nonHeapMemoryMax: %d MB %n", nonHeapMemoryUsage.getMax() / 1024 / 1024);
        // nonHeapMemoryCommitted
        System.out.printf("nonHeapMemoryCommitted: %d MB %n", nonHeapMemoryUsage.getCommitted() / 1024 / 1024);

        // 获取JDK相关信息。包含jdk的版本、安装路径、当前运行jar包路径、运行jar文件名等。
        // jdkVersion
        System.out.printf("jdkVersion: %s %n", System.getProperty("java.version"));
        // java_home
        System.out.printf("java_home: %s %n", System.getProperty("java.home"));
        // jar包路径
        String path = System.getProperty("java.class.path");
        int firstIndex = path.lastIndexOf(System.getProperty("path.separator")) + 1;
        int lastIndex = path.lastIndexOf(File.separator) + 1;
        String jarPath = path.substring(firstIndex, lastIndex);
        System.out.printf("jarPath: %s %n", jarPath);
        // 当前运行jar文件名
        String jarName = path.substring(lastIndex);
        System.out.printf("jarName: %s %n", jarName);

        // 获取java虚拟机类加载相关信息。
        ClassLoadingMXBean classLoadingMxBean = ManagementFactory.getClassLoadingMXBean();
        // LoadedClassCount
        System.out.printf("LoadedClassCount: %d %n", classLoadingMxBean.getLoadedClassCount());
        // TotalLoadedClassCount
        System.out.printf("TotalLoadedClassCount: %d %n", classLoadingMxBean.getTotalLoadedClassCount());
        // UnloadedClassCount
        System.out.printf("UnloadedClassCount: %d %n", classLoadingMxBean.getUnloadedClassCount());

        // 获取操作系统以及主机硬件信息，包含系统名称、版本、物理内存、可用内存等等。
        // 系统版本
        OperatingSystemMXBean operatingSystemMxBean = (OperatingSystemMXBean) ManagementFactoryHelper.getOperatingSystemMXBean();
        // osName
        System.out.printf("osName: %s %n", operatingSystemMxBean.getName());
        // Arch
        System.out.printf("Arch: %s %n", operatingSystemMxBean.getArch());
        // Version
        System.out.printf("Version: %s %n", operatingSystemMxBean.getVersion());
        // 物理内存
        long totalPhysicalMemorySize = operatingSystemMxBean.getTotalPhysicalMemorySize() / 1024 / 1024 / 1024;
        // totalPhysicalMemorySize
        System.out.printf("totalPhysicalMemorySize: %d GB %n", totalPhysicalMemorySize);


    }
}

