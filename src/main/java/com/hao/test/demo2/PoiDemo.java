package com.hao.test.demo2;

import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * poi常用方法
 *
 * @author xu.liang
 * @since 2023/2/14 14:00
 */
public class PoiDemo {

    @SneakyThrows({FileNotFoundException.class, IOException.class})
    public static void main(String[] args) {

        Workbook workbook = new HSSFWorkbook();
        /*==========获取常用对象======begin===========
        //读取文件
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("C:/a.xls"));
        //得到Excel工作簿对象
        Workbook workbook = new HSSFWorkbook(fs);
        //得到工作表对象
        Sheet sheet = workbook.getSheetAt(0);
        //得到工作表的行
        Row row = sheet.getRow(0);
        //得到指定行的单元格
        Cell cell = row.getCell(0);
        //得到单元格样式
        CellType cellType = CellType.forInt(cell.getCellType());
        =========获取常用对象=========end===========*/
        /*=============建立常用对象=======begin=======
        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        //创建工作表
        Sheet sheet= workbook.createSheet("new Sheet");
        //创建行
        Row row = sheet.getRow(0);
        //创建单元格样式
        CellStyle cellStyle = workbook.createCellStyle();
        //在指定行创建指定样式的单元格
        row.createCell(0).setCellStyle(cellStyle);
        //在指定行创建含有指定值的单元格
        row.createCell(0).setCellValue(1);
        ===============建立常用对象=======end========*/
        /*============设置sheet名称和单元格内容======begin======
        Workbook workbook = new HSSFWorkbook();
        workbook.setSheetName(1, "第一张工作表");
        Cell cell = workbook.createSheet().createRow(0).createCell(0);
        cell.setCellValue("单元格内容");
        ============设置sheet名称和单元格内容=======end======*/
        /*============取得sheet的数量========begin====
        Workbook workbook = new HSSFWorkbook();
        workbook.getNumberOfSheets();
        ============取得sheet的数量========end====*/
        //根据index取得sheet对象
        Sheet sheet = workbook.getSheetAt(0);
        //取得有效的行数
        int count = sheet.getLastRowNum();
        //取得一行有效的单元格个数
        Row row = sheet.getRow(0);
        row.getLastCellNum();
        //单元格类型的读写
        Cell cell = row.getCell(0);
        cell.setCellType(CellType.STRING);//设置单元格为String类型
        cell.getNumericCellValue();//读取数值类型的单元格内容
        //设置列宽 行高
        sheet.setColumnWidth(100, 100);
        row.setHeight((short) 100);
        //添加区域 合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 3, 0, 0));
        Row row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("0");
        row1.createCell(1).setCellValue("1");
        row1.createCell(2).setCellValue("2");
        row1.createCell(3).setCellValue("3");
        //保存Excel文件
        String path = "C:/a.xls";
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        workbook.write(fileOutputStream);
        //常用单元格边框格式
        CellStyle style = workbook.createCellStyle();
        // style.setBorderTop(CellStyle.BORDER_DOTTED);//上边框
        // style.setBorderBottom(CellStyle.BORDER_DOTTED);//下边框
        // style.setBorderLeft(CellStyle.BORDER_THIN);//左边框
        // style.setBorderRight(CellStyle.BORDER_DOTTED);//右边框
        // //设置字体
        // Font font = workbook.createFont();
        // font.setFontHeightInPoints((short)11);//设置字体
        // font.setBoldweight(Font.BOLDWEIGHT_NORMAL);//加粗
        // //设置位置
        // style.setFont(font);
        // style.setAlignment(CellStyle.ALIGN_CENTER);//左右居中
        // style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//上下居中
        style.setRotation((short) 10);//单元格内容的旋转的角度
        //设置
        DataFormat dataFormat = workbook.createDataFormat();
        style.setDataFormat(dataFormat.getFormat("0.00%"));//设置单元格数据格式
        cell.setCellFormula("");//给单元格设置公式
        //插入图片
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedImage bufferedImage = ImageIO.read(new File("a.jpg"));
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        //自定义颜色
        Font font1 = workbook.createFont();
        font1.setColor(HSSFColor.RED.index);
        style.setFont(font1);
        cell.setCellStyle(style);
    }

    /**
     * 根据单元不同属性返回字符串数值
     */
    public String getCellStringValue(Cell cell) {
        String cellValue = "";
        switch (CellType.forInt(cell.getCellType())) {
            case _NONE:
            case BOOLEAN:
            case ERROR:
                break;
            case NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                if (cellValue.trim().length() <= 0) {
                    cellValue = "";
                }
                break;
            case FORMULA:
                cell.setCellType(CellType.NUMERIC);
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case BLANK:
                cellValue = "";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + CellType.forInt(cell.getCellType()));
        }
        return cellValue;
    }


}
