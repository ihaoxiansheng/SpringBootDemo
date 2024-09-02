package com.hao.test.year.demo2024.demo9;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * Excel解析工具类
 */
@Slf4j
public class ExcelUtil {

    /**
     * <p> 方法描述: 读取excel文件，使用需要new 一个
     * CommonAnalysisEventListener 监听器对象</p>
     *
     * @param inputStream 文件流
     * @param readRowNum  列表头行数，从readRowNum + 1 行开始导入数据
     * @param clazz       导入数据映射的实体类
     * @param consumer    自定义方法
     */
    public static <T> void readExcel(InputStream inputStream, int readRowNum, Class<T> clazz, Consumer<List<T>> consumer) {
        EasyExcel.read(inputStream, clazz, new CommonAnalysisEventListener<>(consumer)).headRowNumber(readRowNum).sheet().doReadSync();
    }

    public static void writeExcel(HttpServletResponse response, List<?> data, String fileName, String sheetName, Class<?> clazz) {
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        try (InputStream inputStream = createExcelTemplate(StringUtils.isBlank(sheetName) ? "default" : sheetName, "admin")) {
            EasyExcel.write(getOutputStream(fileName, response), clazz)
                    .excelType(ExcelTypeEnum.XLSX)
                    .withTemplate(inputStream)
                    .sheet().registerWriteHandler(horizontalCellStyleStrategy)
                    .doWrite(data);
        } catch (Exception e) {
            log.error("创建导出模版异常：", e);
            throw new RuntimeException("创建导出模版异常！");
        }
    }

    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
            return response.getOutputStream();
        } catch (IOException e) {
            log.error("导出文件失败：", e);
            throw new RuntimeException("导出文件失败！");
        }
    }

    private static <T> AnalysisEventListener<T> getReadListener(Consumer<List<T>> consumer, int threshold) {
        return new AnalysisEventListener<T>() {
            List<T> dataList = new LinkedList<>();

            @Override
            public void invoke(T t, AnalysisContext analysisContext) {
                dataList.add(t);
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
                dataList.clear();
            }
        };
    }

    private static <T> AnalysisEventListener<T> getReadListener(Consumer<List<T>> consumer) {
        return getReadListener(consumer, 1000);
    }

    public static void checkData(String value, Integer length, Boolean isPhone, Boolean isDate, Boolean isNumber, String content) throws IOException {
        if (isDate) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");

            try {
                formatter.parse(value);
            } catch (ParseException var9) {
                throw new RuntimeException(content + "不正确，请重新填写");
            }
        }

        if (length != 0 && value.length() > length) {
            throw new RuntimeException(content + "字数过长");
        } else {
            if (isNumber) {
                try {
                    Long var10 = Long.valueOf(value);
                } catch (NumberFormatException var8) {
                    throw new NumberFormatException(content + "格式不正确，请填写正确的数字格式");
                }
            }

            if (isPhone) {
                Pattern p = Pattern.compile("^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$");
                boolean matches = p.matcher(value).matches();
                if (!matches) {
                    throw new RuntimeException(content + "格式不正确请输入正确的手机号");
                }
            }

        }
    }

    /**
     * <p> 方法描述: 创建带有水印的导出模板，返回输入流 </p>
     *
     * @param sheetName     sheet名称
     * @param watermarkText 水印文字
     */
    private static InputStream createExcelTemplate(String sheetName, String watermarkText) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // 添加一个新的 Sheet
            Sheet sheet = workbook.createSheet(sheetName);
            // 在新的 Workbook 对象中添加文字水印
            addTextWatermark(sheet, watermarkText);
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            log.error("创建导出模版异常", e);
            return null;
        }
    }

    private static void addTextWatermark(Sheet sheet, String watermarkText) throws IOException {
        try (ByteArrayOutputStream waterMark = createWaterMark(watermarkText)) {
            putWaterRemarkToExcel((XSSFSheet) sheet, waterMark.toByteArray());
        }
    }

    public static ByteArrayOutputStream createWaterMark(String content) throws IOException {
        int width = 400;
        int height = 250;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        String fontType = "微软雅黑";
        int fontStyle = Font.PLAIN;
        int fontSize = 16;
        Font font = new Font(fontType, fontStyle, fontSize);
        // 获取Graphics2d对象
        Graphics2D g2d = image.createGraphics();
        image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g2d.dispose();
        g2d = image.createGraphics();
        // 设置字体颜色和透明度，最后一个参数为透明度
        g2d.setColor(new Color(0, 0, 0, 30));
        // 设置字体
        g2d.setStroke(new BasicStroke(1));
        // 设置字体类型 加粗 大小
        g2d.setFont(font);
        // 设置倾斜度
        g2d.rotate(-0.5, (double) image.getWidth() / 2, (double) image.getHeight() / 2);
        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(content, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = -bounds.getY();
        double baseY = y + ascent;
        // 写入水印文字原定高度过小，所以累计写水印，增加高度
        g2d.drawString(content, (int) x, (int) baseY);
        // 设置透明度
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        // 释放对象
        g2d.dispose();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);
        return os;
    }

    public static void putWaterRemarkToExcel(XSSFSheet sheet, byte[] bytes) {
        // add relation from sheet to the picture data
        XSSFWorkbook workbook = sheet.getWorkbook();
        int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        String rID = sheet.addRelation(null, XSSFRelation.IMAGES, workbook.getAllPictures().get(pictureIdx))
                .getRelationship().getId();
        // set background picture to sheet
        sheet.getCTWorksheet().addNewPicture().setId(rID);
    }
}
