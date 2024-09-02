package com.hao.test.year.demo2024.demo9;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
public class ExcelReaderExample {

    public static void main(String[] args) {
        try {

            // ClassPathResource classPathResource = new ClassPathResource("业务影响清单.xlsx");
            ClassPathResource classPathResource = new ClassPathResource("故障处理过程模版.xlsx");
            InputStream inputStream = classPathResource.getInputStream();

            // 定义开始读取数据的行号，这里假设第一行是标题行，数据从第二行开始
            int readRowNum = 1;

            // 定义映射数据的类
            Class<ExampleDataClass> clazz = ExampleDataClass.class;

            // 定义一个消费者来处理从Excel文件中读取的数据列表
            Consumer<List<ExampleDataClass>> consumer = dataList -> {
                // 处理数据列表
                for (ExampleDataClass data : dataList) {
                    System.out.println(data);
                }
            };

            // 调用 readExcel 方法
            ExcelUtil.readExcel(inputStream, readRowNum, clazz, consumer);

        } catch (Exception e) {
            log.error("读取Excel文件失败", e);
        }
    }
}
