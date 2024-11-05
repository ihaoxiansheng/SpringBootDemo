package com.hao.test.year.demo2024.demo10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Stream API 和 普通 for 循环性能对比
 *
 * @author xu.liang
 * @since 2024/10/29 15:07
 */
public class PerformanceTest {
    public static void main(String[] args) {
        // 创建示例数据
        List<Map<String, Object>> tableListMap = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("TABLE_NAME", "table_" + i);
            tableListMap.add(map);
        }

        // 测试 Stream API
        long startTime = System.currentTimeMillis();
        List<String> tableNamesWithStream = getTableNamesWithStream(tableListMap);
        long endTime = System.currentTimeMillis();
        System.out.println("Stream API: " + (endTime - startTime) + " ms");

        // 测试传统 for 循环
        startTime = System.currentTimeMillis();
        List<String> tableNamesWithForLoop = getTableNamesWithForLoop(tableListMap);
        endTime = System.currentTimeMillis();
        System.out.println("For Loop: " + (endTime - startTime) + " ms");
    }

    public static List<String> getTableNamesWithStream(List<Map<String, Object>> tableListMap) {
        return tableListMap.stream()
                .map(map -> (String) map.values().iterator().next())
                .collect(Collectors.toList());
    }

    public static List<String> getTableNamesWithForLoop(List<Map<String, Object>> tableListMap) {
        List<String> tableList = new ArrayList<>();
        for (Map<String, Object> map : tableListMap) {
            map.values().forEach(tableName -> tableList.add(tableName.toString()));
        }
        return tableList;
    }
}
