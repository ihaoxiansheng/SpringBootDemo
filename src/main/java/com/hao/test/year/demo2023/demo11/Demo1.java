package com.hao.test.year.demo2023.demo11;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.XmlUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author xu.liang
 * @since 2023/11/20 16:20
 */
public class Demo1 {
    public static void main(String[] args) {

        String aa = "<Return><ReturnCode>0</ReturnCode><dis_id>SHGJ-202311-173</dis_id><ReturnString>OK!</ReturnString></Return>";
        Map<String, Object> map = XmlUtil.xmlToMap(aa);
        System.out.println("map = " + map);

        String test = "";
        String trim = test.trim();
        System.out.println("trim = " + trim);

        String num = "";
        boolean number = NumberUtil.isNumber(num);
        System.out.println("number = " + number);

        // String bwpBuld = "15232421";
        String bwpBuld = "152";
        String strBwpBuld = bwpBuld;
        int length = bwpBuld.length();
        strBwpBuld = String.format("%0" + 4 + "d", Integer.parseInt(strBwpBuld));
        System.out.println("strBwpBuld = " + strBwpBuld);

        String timestamp = String.valueOf(DateUtil.current());
        System.out.println("timestamp = " + timestamp);

        String a = null;
        String s = String.valueOf(a);
        System.out.println("s = " + s);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hh", "aaa");
        System.out.println("hh = " + jsonObject.getString("hh"));
        String s1 = String.valueOf(jsonObject.get("aa"));
        String s11 = jsonObject.getString("aa");
        System.out.println("s1 = " + s1);
        System.out.println("s11 = " + s11);

    }
}
