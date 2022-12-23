package com.hao.springbootdemo.test.reflect;

import java.lang.reflect.Method;

/**
 * @author xu.liang
 * @since 2022/11/1 09:38
 */
public class InvokeTest {

    public int add(int param1, int param2) {
        return param1 + param2;
    }

    public String echo(String mesg) {
        return "echo " + mesg;
    }

    public static void main(String[] args) throws Exception {
        Class<InvokeTest> classType = InvokeTest.class;
        Object invokertester = classType.newInstance();

        Method addMethod = classType.getMethod("add", new Class[]{int.class, int.class});
        //Method类的invoke(Object obj,Object args[])方法接收的参数必须为对象，
        //如果参数为基本类型数据，必须转换为相应的包装类型的对象。invoke()方法的返回值总是对象，
        //如果实际被调用的方法的返回类型是基本类型数据，那么invoke()方法会把它转换为相应的包装类型的对象，
        //再将其返回
        Object result = addMethod.invoke(invokertester, new Object[]{100, 200});
        //在jdk5.0中有了装箱 拆箱机制 new Integer(100)可以用100来代替，系统会自动在int 和integer之间转换
        System.out.println(result);

        Method echoMethod = classType.getMethod("echo", new Class[]{String.class});
        result = echoMethod.invoke(invokertester, new Object[]{"hello"});
        System.out.println(result);


        try {
            Class<?> c = Class.forName("java.util.HashSet");
            Object o = c.newInstance();
            Method[] methods = c.getDeclaredMethods();
            for(Method method : methods){
                System.out.println("method====" + method);
            }
            Method m1 = c.getMethod("add", Object.class);
            m1.invoke(o, "cyq");
            m1.invoke(o, "hello");
            m1.invoke(o, "java");
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
