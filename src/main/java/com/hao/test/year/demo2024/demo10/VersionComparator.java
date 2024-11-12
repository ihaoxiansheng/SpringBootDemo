package com.hao.test.year.demo2024.demo10;

public class VersionComparator {

    public static void main(String[] args) {
        String version1 = "1.1.2";
        String version2 = "1.2";

        int comparisonResult = compareVersions(version1, version2);

        if (comparisonResult < 0) {
            System.out.println(version1 + " 小于 " + version2);
        } else if (comparisonResult > 0) {
            System.out.println(version1 + " 大于 " + version2);
        } else {
            System.out.println(version1 + " 等于 " + version2);
        }
    }

    public static int compareVersions(String version1, String version2) {
        // 使用点分割版本号
        String[] parts1 = version1.split("\\.");
        String[] parts2 = version2.split("\\.");

        // 找到最大长度
        int maxLength = Math.max(parts1.length, parts2.length);

        for (int i = 0; i < maxLength; i++) {
            // 获取当前部分，如果没有部分则默认为0
            int v1 = i < parts1.length ? Integer.parseInt(parts1[i]) : 0;
            int v2 = i < parts2.length ? Integer.parseInt(parts2[i]) : 0;

            // 比较当前部分
            if (v1 < v2) {
                return -1; // version1 小于 version2
            } else if (v1 > v2) {
                return 1; // version1 大于 version2
            }
        }
        return 0; // version1 等于 version2
    }
}
