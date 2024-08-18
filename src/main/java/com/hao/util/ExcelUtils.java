package com.hao.util;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.baomidou.mybatisplus.core.injector.methods.Insert;
import com.hao.util.exception.GlobalException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * excel导入导出工具类
 *
 * @author xu.liang
 * @since 2024/3/7 13:43
 */
public class ExcelUtils {

    /**
     * 数据导出
     *
     * @param response
     * @param request
     * @param map
     * @param fileName  导出的文件名
     * @param titleName excel的标题
     * @param list      导出的数据
     * @param clazz     list中的数据类型
     */
    public static <T> void exportData(HttpServletResponse response, HttpServletRequest request, ModelMap map, String fileName, String titleName, List<T> list, Class<T> clazz) {
        ExportParams params = new ExportParams(titleName, titleName, ExcelType.XSSF);
        map.put(NormalExcelConstants.DATA_LIST, list);
        map.put(NormalExcelConstants.CLASS, clazz);
        map.put(NormalExcelConstants.PARAMS, params);
        if (StringUtils.isEmpty(fileName)) {
            fileName = "导出数据" + System.currentTimeMillis();
        }
        map.put("fileName", fileName);
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    public static <T> List<T> importData(MultipartFile file, Class<T> clazz) {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        params.setNeedVerfiy(true);
        params.setVerfiyGroup(new Class[]{Insert.class});
        List<T> templateUserList;
        try {
            templateUserList = ExcelImportUtil.importExcel(file.getInputStream(), clazz, params);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException("导入失败,请确定数据格式是否正确");
        }
        return templateUserList;
    }
}
