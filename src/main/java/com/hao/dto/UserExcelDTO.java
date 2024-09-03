package com.hao.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import lombok.Data;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 * @author xu.liang
 * @since 2024/3/8 15:35
 */
@Data
public class UserExcelDTO {

    @ExcelProperty("id")
    @HeadStyle(horizontalAlignment = HorizontalAlignment.CENTER)
    @ContentStyle(horizontalAlignment = HorizontalAlignment.CENTER)
    @ColumnWidth(10)
    private String id;

    @ExcelProperty("账号")
    @HeadStyle(horizontalAlignment = HorizontalAlignment.CENTER)
    @ContentStyle(horizontalAlignment = HorizontalAlignment.CENTER)
    @ColumnWidth(20)
    private String username;

    @ExcelProperty("邮箱")
    @HeadStyle(horizontalAlignment = HorizontalAlignment.CENTER)
    @ContentStyle(horizontalAlignment = HorizontalAlignment.CENTER)
    @ColumnWidth(40)
    private String email;

}
