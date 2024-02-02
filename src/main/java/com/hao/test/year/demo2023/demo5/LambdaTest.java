package com.hao.test.year.demo2023.demo5;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * MybatisPlus Lambda 属性获取原理
 *
 * @author xu.liang
 * @since 2023/5/11 14:47
 */
public class LambdaTest {

    public class Entity {
        private String userName;

        public String getUserName() {
            return this.userName;
        }
    }

    public interface FieldFunction<T, R> extends Function<T, R>, Serializable {

    }

    public static <T> String getFieldName(FieldFunction<T, ?> func) {
        try {
            Method method = func.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(true);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(func);
            String getter = serializedLambda.getImplMethodName();
            return "获取到方法名称 = " + getter;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(getFieldName(Entity::getUserName));
    }
}
