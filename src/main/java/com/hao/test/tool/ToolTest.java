package com.hao.test.tool;

import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author xu.liang
 * @since 2022/11/8 09:18
 */
public class ToolTest {
    public static void main(String[] args) {
        String v1 = " abc def abc";
        strTrimWhitespace(v1);

    }

    public static void strTrimWhitespace(String v1) {

        System.out.println("StringUtils.trimWhitespace(v1) =" + StringUtils.trimWhitespace(v1));
        System.out.println("StringUtils.trimAllWhitespace(v1) =" + StringUtils.trimAllWhitespace(v1));
        System.out.println("StringUtils.trimLeadingWhitespace(v1) =" + StringUtils.trimLeadingWhitespace(v1));

        System.out.println("StringUtils.trimTrailingWhitespace(v1) = " + StringUtils.trimTrailingWhitespace(v1));

        String replace = v1.replace("abc", "X");
        System.out.println("replace = " + replace);

        Random random = new Random();
        System.out.println("Math.random() = " + Math.random());

        System.out.println("random.nextDouble() = " + random.nextDouble());
        System.out.println("random.nextInt() = " + random.nextInt());
        System.out.println("random.nextLong() = " + random.nextLong());

        String[] aa = {"11", "22", "33"};
        String[] bb = {"44", "55", "6б", "11"};
        String[] cс = {"77", "88", "99"};
        // 合并两个数组
        String[] dd = new String[aa.length + bb.length];
        System.arraycopy(aa, 0, dd, 0, aa.length);
        System.arraycopy(bb, 0, dd, aa.length, bb.length);
        System.out.println("dd.toString() = " + Arrays.toString(dd));

        List<String> list = new ArrayList<>();

        String a = "11";
        String b = "11";
        String c = "11";
        list.add(a);
        list.add(b);
        list.add(c);
        System.out.println("list = " + list);
        list.addAll(Arrays.asList(bb));
        System.out.println("list1 = " + list);
        Set<String> set = new HashSet<>(list);
        System.out.println("set = " + set);
    }


}
