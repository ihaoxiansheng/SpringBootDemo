package com.hao.test.year.demo2024.demo2;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 提取汉字首字母工具类
 *
 * @author xu.liang
 * @since 2024/2/4 15:15
 */
public class ChineseToFirstLetterUtil {

    /**
     * 汉字转拼音大写首字母
     */
    public static String ChineseToFirstLetter(String c) {
        StringBuilder string = new StringBuilder();
        char b;
        int a = c.length();
        for (int k = 0; k < a; k++) {
            b = c.charAt(k);
            String d = String.valueOf(b);
            String str = converterToFirstSpell(d);
            String s = str.toUpperCase();
            char h;
            for (int y = 0; y <= 0; y++) {
                h = s.charAt(0);
                string.append(h);
            }
        }
        return string.toString();
    }

    /**
     * 汉字转拼音
     */
    public static String converterToFirstSpell(String chines) {
        StringBuilder pinyinName = new StringBuilder();
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            String s = String.valueOf(nameChar[i]);
            if (s.matches("[\\u4e00-\\u9fa5]")) {
                try {
                    String[] mPinyinArray = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);
                    pinyinName.append(mPinyinArray[0]);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName.append(nameChar[i]);
            }
        }
        return pinyinName.toString();
    }

    public static void main(String[] args) {
        System.out.println(ChineseToFirstLetter("干饭人干饭魂干饭都是人上人"));
        System.out.println(converterToFirstSpell("今日干饭不狠，明日地位不稳"));
    }

}
