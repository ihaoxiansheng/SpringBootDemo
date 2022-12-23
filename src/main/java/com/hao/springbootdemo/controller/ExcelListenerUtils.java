package com.hao.springbootdemo.controller;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author：xie.yuen
 * @date:
 */
public class ExcelListenerUtils extends AnalysisEventListener<Map<Integer, String>> {
    //Excel数据缓存结构
    private List<Map<Integer, Map<Integer, String>>> list;
    //Excel表头（列名）数据缓存结构
    private Map<Integer, String> headTitleMap = new HashMap<>();

    public ExcelListenerUtils() {
        list = new ArrayList<>();
    }

    /** 解析表头外的所有行数据 **/
    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        System.out.println("解析单行数据：" + JSON.toJSONString(data));
        Map<Integer, Map<Integer, String>> map = new HashMap<>();
        map.put(context.readRowHolder().getRowIndex(), data);
        list.add(map);
    }

    /** 解析完的后置操作 **/
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("所有数据解析完成");
    }

    /** 解析表头数据 **/
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        headTitleMap = headMap;
    }

    public List<Map<Integer, Map<Integer, String>>> getList() {
        return list;
    }

    public Map<Integer, String> getHeadTitleMap() {
        return headTitleMap;
    }
}
