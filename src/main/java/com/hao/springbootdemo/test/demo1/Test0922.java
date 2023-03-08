package com.hao.springbootdemo.test.demo1;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hao.springbootdemo.test.demo3.DataTypeAdaptor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author xu.liang
 * @since 2022/9/22 10:26
 */
public class Test0922 {

    private final static Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
    private final static Gson GSON1 =
            new GsonBuilder().registerTypeAdapter(new TypeToken<Map<String,Object>>(){}.getType(),new DataTypeAdaptor()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    public static void main(String[] args) {

        Predicate<String> predicate = list -> list.contains("好");

        List<String> stringList = Arrays.asList("好人", "好笑", "好客", "好文", "微笑");

        // List<String> filterList = StringFilter.filter(stringList, predicate);

        System.out.println("stringList = " + stringList);


        boolean test = predicate.test(stringList.toString());
        System.out.println("test = " + test);

        Gson gson = new Gson();
        String str = gson.toJson(new Object());
        String str1 = gson.toJson(new Object() {
        });
        System.out.println("str = " + str);
        System.out.println("str1 = " + str1);

        String[] strList = new String[]{"a", "b", "c"};
        List<String> strings = Arrays.asList(strList);
        System.out.println("strList = " + strings);

        String[] strList2 = new String[]{"a", "b", "c", "d"};
        List<String> strings2 = Arrays.asList(strList2);

        boolean b = strings2.containsAll(strings);
        System.out.println("b = " + b);

        GSON.toJson(new Date(), System.out);
        System.out.println();
        String dateStr = GSON.toJson(new Date());
        System.out.println("dateStr = " + dateStr);

        Date date = new Date();
        String dateJson = GSON.toJson(date);
        System.out.println(date);
        System.out.println("date  = " + date.getTime());
        Date date2 = GSON.fromJson(dateJson, Date.class);
        System.out.println(date2);
        System.out.println("date2 = " + date2.getTime());
        System.out.println(date2.equals(date));// 定义时间是yyyy-MM-dd HH:mm:ss.SSS相等，定义为yyyy-MM-dd HH:mm:ss不相等
        System.out.println(date2 == date);

    }
}
