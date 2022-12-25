package com.hao.springbootdemo.test.database;

/**
 * @author xu.liang
 * @since 2022/11/2 16:05
 */
public class TransferTest {

    /**
     * 将以下划线分隔的数据库字段转换为驼峰风格的字符串
     */
    public static String fieldToCamelCasing(String columnName) {
        columnName = "str";
        // String columnName = "str_split_Name";
        if (!columnName.contains("_")) {
            System.out.println("columnName = " + columnName);
            return columnName;
        }
        String[] array = columnName.split("_");
        StringBuffer sbu = new StringBuffer();
        for (String cn : array) {
            cn = cn.toLowerCase();
            sbu.append(cn.substring(0, 1).toUpperCase()).append(cn.substring(1));
        }
        return sbu.toString();
    }

    /**
     * 将驼峰风格的字符串转换为以下划线分隔的数据库字段
     * @see TransferTest#fieldToCamelCasing(String)
     */
    public static String camelCasingToField(String fieldName) {
        if (fieldName == null) {
            return null;
        }
        StringBuffer sbu = new StringBuffer();
        int length = fieldName.length();
        for (int i = 0; i < length; i++) {
            char c = fieldName.charAt(i);
            if ('A' <= c && 'Z' >= c) {
                sbu.append("_").append((char) (c + 32));
            } else {
                sbu.append(fieldName.charAt(i));
            }
        }
        return sbu.toString();
    }

    public static void main(String[] args) {
        String fieldToCamelCasing = fieldToCamelCasing("start_mid_end");
        System.out.println("fieldToCamelCasing = " + fieldToCamelCasing);

        String camelCasingToField = camelCasingToField(fieldToCamelCasing);
        System.out.println("camelCasingToField = " + camelCasingToField);
    }

}
