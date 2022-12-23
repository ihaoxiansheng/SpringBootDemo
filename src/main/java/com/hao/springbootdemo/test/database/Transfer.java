package com.hao.springbootdemo.test.database;

/**
 * @author xu.liang
 * @since 2022/11/2 16:05
 */
public class Transfer {

    /**
     * 将以下划线分隔的数据库字段转换为驼峰风格的字符串
     */
    public static String changeColumnToFieldName(String columnName) {
        String[] array = columnName.split("_");
        StringBuffer sb = null;
        for (String cn : array) {
            cn = cn.toLowerCase();
            if (sb == null) {
                sb = new StringBuffer(cn);
                continue;
            }
            sb.append(cn.substring(0, 1).toUpperCase()).append(cn.substring(1));
        }
        return sb.toString();
    }

    /**
     * 将驼峰风格的字符串转换为以下划线分隔的数据库字段
     * @see Transfer#changeColumnToFieldName(String)
     */
    public static String changeFieldToColumnName(String fieldName) {
        if (fieldName == null) {
            return null;
        }
        StringBuffer columnName = new StringBuffer();
        int length = fieldName.length();
        for (int i = 0; i < length; i++) {
            char c = fieldName.charAt(i);
            if ('A' <= c && 'Z' >= c) {
                columnName.append("_").append((char) (c + 32));
            } else {
                columnName.append(fieldName.charAt(i));
            }
        }
        return columnName.toString();
    }

    public static void main(String[] args) {
        String changeColumnToFieldName = changeColumnToFieldName("start_mid_end");
        System.out.println("changeColumnToFieldName = " + changeColumnToFieldName);

        String startMidEnd = changeFieldToColumnName("startMidEnd");
        System.out.println("startMidEnd = " + startMidEnd);
    }

}
