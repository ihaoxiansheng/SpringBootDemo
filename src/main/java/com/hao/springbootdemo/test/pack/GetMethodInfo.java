package com.hao.springbootdemo.test.pack;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xu.liang
 * @since 2022/10/28 09:59
 */
public class GetMethodInfo {
    public static void main(String[] args) {
        getMethodNames();

    }

    public static void getMethodNames() {
        Map<String, Method> uniqueMethods = new HashMap<>();
        Class<?> currentClass = GetMethodInfo.class;
        System.out.println("currentClass = " + currentClass);
        Method[] declaredMethods = currentClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println("method.getName() = " + method.getName());
            // System.out.println("method.getParameterCount() = " + method.getParameterCount());
            // System.out.println("method.getParameters() = " + Arrays.toString(method.getParameters()));
            // Class<?>[] parameterTypes = method.getParameterTypes();
            // if (parameterTypes.length == 0) {
            //     System.out.println("method.getName() = " + method.getName() + "方法中没有参数");
            // }
            // for (Class<?> parameter : parameterTypes) {
            //     System.out.println("parameter = " + parameter);
            // }
            // System.out.println("method.getParameterTypes() = " + Arrays.toString(method.getParameterTypes()));
            // System.out.println("method.getReturnType() = " + method.getReturnType());
            // System.out.println("method.getModifiers() = " + method.getModifiers());
            // System.out.println("method.getTypeParameters() = " + Arrays.toString(method.getTypeParameters()));
            // System.out.println("method.isAccessible() = " + method.isAccessible());
            // System.out.println("method.getParameterAnnotations() = " + Arrays.deepToString(method.getParameterAnnotations()));
            // System.out.println("method.getDeclaringClass().get = " + Arrays.toString(method.getDeclaringClass().getDeclaredFields()));

        }
        for (Method m : GetMethodInfo.class.getDeclaredMethods()) {
            System.out.println("--------------------");
            System.out.println("method: " + m.getName());
            System.out.println("return: " + m.getReturnType().getName());
            for (Parameter p : m.getParameters()) {
                // System.out.println("parameter:" + p.getType().getName() + "," + p.getName());
                System.out.println("parameterName = " + p.getName());
            }
        }


    }
}
