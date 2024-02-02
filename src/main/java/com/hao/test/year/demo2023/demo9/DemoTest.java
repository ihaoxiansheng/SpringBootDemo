package com.hao.test.year.demo2023.demo9;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xu.liang
 * @since 2023/9/5 09:36
 */
public class DemoTest {

    @SneakyThrows
    public static void main(String[] args) {

//        Throwable ex = new Throwable();
//        StackTraceElement[] stackElements = ex.getStackTrace();
//        if (stackElements != null) {
//            for (StackTraceElement stackElement : stackElements) {
//                System.out.print(stackElement.getClassName());
//                System.out.print(stackElement.getFileName());
//                System.out.print(stackElement.getLineNumber());
//                System.out.println(stackElement.getMethodName());
//                System.out.println("-----------------------------------");
//            }
//        }
//
//        Exception e = new Exception("this is a log");
//        e.printStackTrace();
//        // 延迟才可以看出效果
//        Thread.sleep(1000);
//        System.out.println("-------------以上是异常详细信息----------------");
//
//        System.out.println("打印异常1：" + e);
//        String stackTrace = ExceptionUtils.getStackTrace(e);
//        System.out.println("打印异常2：" + stackTrace);

        String a = "hello";
        String b;
        do {
            System.out.println("a = " + a);
            b = "1";
        } while ("".equals(b));


        List<String> list1 = new LinkedList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("11");

        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.add("5");
        list2.add("6");
        list1.removeAll(list2);
        System.out.println("list1 = " + list1);
        System.out.println("list2 = " + list2);
        String[] aa = {"1", "2", "3", "4", "5", "6"};
        String string = Arrays.toString(aa);
        System.out.println("string = " + string);

        String yyyyMMddHHmmssSSS = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        System.out.println("yyyyMMddHHmmssSSS = " + yyyyMMddHHmmssSSS);

        UUID uuid = UUID.randomUUID();
        System.out.println("uuid = " + uuid);

        // 去重合并
        List<String> distinctList = Stream.concat(list1.stream(), list2.stream()).distinct().collect(Collectors.toList());
        System.out.println("distinctList = " + distinctList);

//        String publickey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAO46RHV8MLaC0A1sw15q8dNxBurEmgHwFVP1AvA+3+2mKdDL4uzg7tlM1JdgTjV4QOQ79bs8ba1L4ao2I1vvFR8CAwEAAQ==";
//        String password = "QImPIH1+jPtqz7fBEFWl3iXngrmbA8m+n/djyvWuE0OJQXOuvQPJGe8/BqLNn3NO7HioBWNX+XpjbgbaDNSJmw==";
//        String pwd = ConfigTools.decrypt(publickey, password);
//        System.out.println(pwd);

    }

}
