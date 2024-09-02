package com.hao.test.year.demo2024.demo9;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExampleDataClass {

    @ExcelProperty("4G站号")
    private String no;
    @ExcelProperty("部门/人员")
    private String no1;

    @ExcelProperty("设备厂家")
    private String name;
    @ExcelProperty("备注")
    private String name1;

}
