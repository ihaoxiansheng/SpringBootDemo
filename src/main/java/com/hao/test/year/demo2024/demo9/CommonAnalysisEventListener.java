package com.hao.test.year.demo2024.demo9;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 自定义监听器，用于处理 Excel 文件的读取事件
 */
@Slf4j
public class CommonAnalysisEventListener<T> extends AnalysisEventListener<T> {

    private static final int threshold = 1000;

    private final Consumer<List<T>> consumer;
    private final List<T> dataList = new ArrayList<>();

    public CommonAnalysisEventListener(Consumer<List<T>> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void invoke(T data, AnalysisContext analysisContext) {
        dataList.add(data);
        // 达到 threshold 了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (dataList.size() >= threshold) {
            consumer.accept(dataList);
            dataList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (!dataList.isEmpty()) {
            consumer.accept(dataList);
        }
        log.error("所有数据解析完成！");
        dataList.clear();
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合doAfterAllAnalysedHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            log.error("第{}行，第{}列解析异常", excelDataConvertException.getRowIndex() + 1, excelDataConvertException.getColumnIndex() + 1);
        } else {
            super.onException(exception, context);
        }
    }
}
