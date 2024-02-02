package com.hao.test.year.demo2023.demo1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xu.liang
 * @since 2022/3/16 20:44
 */
public class Test02 {
    public static void main(String[] args) {


        List<String> list = Arrays.asList("1.0.0 1.0.1 1.0.2 1.0.3 1.2.1".split(" "));
        System.out.println(list);
        System.out.println("最大值: " + Collections.max(list));
        System.out.println("最小值: " + Collections.min(list));


        String modelCode = "ee_order_xz_omadddasdasdasdasdee_order_xz_omadddasdasdasdasdee_order_xz_omadddasdasdasdasdee_order_xz_omadddasdasdasdasd";
        if (modelCode.length() > 20) {
//            throw new Exception("太长了");
        }

        boolean check = dateCompare("2000-01-01 00:00:00");
        System.out.println("check = " + check);

        List<Integer> asList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> asList1 = Arrays.asList(1, 2, 3, 10, 5, 6);
        if (asList.containsAll(asList1)) {
            System.out.println("包含");
        } else {
            System.out.println("不包含");
        }


        Random random = new Random();
        random.nextInt(100);
        System.out.println("random = " + random.nextInt());

        double random1 = Math.random();
        System.out.println("random1 = " + random1);


        List<String> lists = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> listNew = lists.stream().flatMap(s -> {
            // 将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        }).collect(Collectors.toList());
        System.out.println("处理前的集合：" + lists);
        System.out.println("处理后的集合：" + listNew);


        List<Integer> list1 = Arrays.asList(1, 2, 4, 3, 5, 6, 7, 8, 9);
        list1.sort(Integer::compareTo);
        list1.sort(Comparator.naturalOrder());
        list1.sort(Comparator.reverseOrder());

        System.out.println("list1 = " + list1);

        List<String> list2 = Arrays.asList("1", "2", "4", "3", "5", "6", "7", "8", "9");
        String[] strings = list2.toArray(new String[0]);
        // for (String string : strings) {
        //     System.out.println("string = " + string);
        // }
        List<String> strList = new ArrayList<>();
        strList.add("1");
        strList.add("2");
        strList.add("4");
        strList.add("3");
        strList.add("5");
        strList.add("6");
        strList.add("7");
        strList.add("8");
        strList.add("9");
        System.out.println("strList = " + strList);
        String remove = strList.remove(1);
        System.out.println("remove = " + remove);
        System.out.println("strList = " + strList);

        String createTable = "create table `asdfg` ()";
        String tableNamezz = createTable.substring(createTable.indexOf("`") + 1, createTable.indexOf("`", createTable.indexOf("`") + 1));
        System.out.println("tableNamezz = " + tableNamezz);
        int index = createTable.indexOf(tableNamezz);
        System.out.println("index = " + index);

        System.out.println(tableNamezz.length());

        String aa = createTable.replaceFirst(tableNamezz, "aa");
        System.out.println("aa = " + aa);


        List<Map<String, String>> modelCodeList = new ArrayList<>();
        Map<String, String> oldAndNewCodeMap = new HashMap<>();
        oldAndNewCodeMap.put("1", "2");
        oldAndNewCodeMap.put("3", "4");
        modelCodeList.add(oldAndNewCodeMap);
        System.out.println("oldAndNewCodeMap = " + oldAndNewCodeMap);
        System.out.println("modelCodeList = " + modelCodeList);

    }


    public static boolean dateCompare(String time) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localTime = LocalDateTime.parse(time, dtf);
        System.out.println("LocalDateTime.now() = " + LocalDateTime.now());
        return LocalDateTime.now().isAfter(localTime);
    }

}
