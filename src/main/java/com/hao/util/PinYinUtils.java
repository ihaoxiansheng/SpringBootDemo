package com.hao.util;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 拼音工具类
 *
 * @author xu.liang
 * @since 2024/10/11 15:48
 */
public class PinYinUtils {

    /**
     * 获取中文首字母
     *
     * @param chinese 中文字符串
     * @return 中文首字母
     */
    public static String getFirstLetter(String chinese) {
        StringBuilder pinyin = new StringBuilder();
        for (char c : chinese.toCharArray()) {

            if (Character.isDigit(c)) {
                // 如果是数字，加前缀"ZZZ"，放在靠后位置
                if (pinyin.length() > 0 && pinyin.toString().charAt(0) == 'Z') {
                    pinyin.append(c);
                } else {
                    pinyin.append("ZZZ").append(c);
                }
            } else if (Character.toString(c).matches("[\\u4E00-\\u9FA5]")) {
                // 只对汉字转换拼音
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
                if (pinyinArray != null) {
                    pinyin.append(pinyinArray[0].charAt(0));
                }
            } else {
                pinyin.append(c);
            }
        }
        return pinyin.toString().toUpperCase();
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("ggg");
        list.add("aaa");
        list.add("as");
        list.add("AI工具");
        list.add("安全");
        List<String> collect = list.stream().sorted(Comparator.comparing(str -> {
            String firstLetter = PinYinUtils.getFirstLetter(str);
            System.out.println("firstLetter = " + firstLetter);
            return firstLetter;
        })).collect(Collectors.toList());
        System.out.println("collect = " + collect);

    }

}
