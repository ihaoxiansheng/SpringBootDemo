package com.hao.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hao.dto.UserExcelDTO;
import com.hao.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author xu.liang
 * @since 2022/12/15 09:37
 */
@RestController
@RequestMapping("")
@Slf4j
@Api(tags = "Excel")
public class ExcelController {

    @PostMapping("/importExcel")
    public List<Map<String, String>> importExcel(@RequestParam("valueMap") String params, @RequestParam("file") MultipartFile file) {
        log.info("===============导入excel数据入参：params：{}=================", params);

        JSONObject jsonObject = JSON.parseObject(params);
        // 使用监听器处理文件流
        ExcelListenerUtils parseListener = new ExcelListenerUtils();
        try {
            EasyExcel.read(file.getInputStream(), parseListener).sheet().doRead();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        // 获取Listener缓存的数据结构
        List<Map<Integer, Map<Integer, String>>> list = parseListener.getList();
        Map<Integer, String> headTitleMap = parseListener.getHeadTitleMap();

        // 将表头为key，数据为val组合新的Map，用于转换JSON格式
        List<Map<String, String>> mapList = new ArrayList<>();
        for (Map<Integer, Map<Integer, String>> integerMapMap : list) {
            integerMapMap.forEach((k, l) -> {
                Map<String, String> map = new LinkedHashMap<>();
                l.forEach((y, z) -> {
                    map.put((String) jsonObject.get(headTitleMap.get(y)), z);
                });
                mapList.add(map);
            });
        }
        return mapList;
    }

    @PostMapping("/exportExcel")
    @ApiOperation(value = "导出excel模板", produces = MediaType.APPLICATION_JSON_VALUE)
    public void exportExcel(HttpServletResponse response, @RequestBody List<String> headList) throws Exception {
        log.info("===============导出excel模板入参：headList：{}=================", headList);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 不设置前端无法从header获取文件名
        response.setHeader("Access-Control-Expose-Headers", "filename");
        // 防止中文乱码
        String fileName = URLEncoder.encode("模板.xlsx", "utf-8").replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        response.setHeader("filename", fileName);
//        ExcelUtil.dynamicHeadWrite(response, headList);

        List<List<Object>> initList = Lists.newArrayList();
        List<Object> data = Lists.newArrayList();
        for (int i = 0; i < headList.size(); i++) {
            data.add("测试数据");
        }
        initList.add(data);
//        ExcelWriterBuilder excelWriterBuilder = new ExcelWriterBuilder();
//        excelWriterBuilder.file(response.getOutputStream());
//        if (!CollectionUtils.isEmpty(frontList)) {
//            excelWriterBuilder.head(head(frontList));
//        }
        EasyExcel.write(response.getOutputStream()).autoCloseStream(Boolean.FALSE)
                .registerWriteHandler(new CustomizeColumnWidth()).registerWriteHandler(getHeadAndContentStyle())
                .head(getHead(headList)).sheet("模板")
                //获取数据填充
                .doWrite(initList);
//        excelWriterBuilder.sheet("模板").doWrite(initList);
        log.info("导出excel模板完成");
    }

    @SneakyThrows
    @PostMapping("/exportData")
    @ApiOperation("导出excel清单数据")
    public void exportData(HttpServletResponse response, List<User> userList) {
        log.info("==========================导出清单数据开始==========================");

        List<UserExcelDTO> dtoList = new ArrayList<>();
        int size = userList.size();
        for (int i = 0; i < size; i++) {
            User lcpSceneEntity = userList.get(i);
            UserExcelDTO userExcelDTO = new UserExcelDTO();
            userExcelDTO.setId(String.valueOf(i + 1));
            userExcelDTO.setUsername(lcpSceneEntity.getName());
            userExcelDTO.setEmail(lcpSceneEntity.getEmail());
            dtoList.add(userExcelDTO);
        }
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 不设置前端无法从header获取文件名
        response.setHeader("Access-Control-Expose-Headers", "filename");
        // 防止中文乱码
        String fileName = URLEncoder.encode("用户清单.xlsx", "utf-8").replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setHeader("filename", fileName);
        // 创建excel工作簿
        EasyExcel.write(response.getOutputStream(), UserExcelDTO.class).sheet("用户清单").doWrite(dtoList);

        log.info("==========================导出清单数据完成，共计【{}】条==========================", size);

        // 前端代码
   /*     exportData() {
            this.exportDataLoading = true
            axios({
                    method: 'post',
                    url: `http://192.168.xxx.xxx:8888/demo/exportData`,
            headers: {
                // 传入登录token鉴权
                token: $gc.getToken(),
                        'Content-Type': 'application/json'
            },
            // 通过body传参，后端通过  @RequestBody 注解获取参数
            data: this.tableData,
                    withCredentials: false,
                    responseType: 'arraybuffer'
      }).then(res => {
        const fileUrl = window.URL.createObjectURL(new Blob([res.data]))
            // 创建超链接
        const fileLink = document.createElement('a')
            fileLink.href = fileUrl
            // 设置下载文件名
            let filename = res.headers['filename']
            // let filename = '表格.xls'
            // 解决中文乱码
            filename = window.decodeURI(filename)
            fileLink.setAttribute('download', filename)
            document.body.appendChild(fileLink)
            // 模拟人工点击下载超链接
            fileLink.click()
            // 释放资源
            document.body.removeChild(fileLink)
            window.URL.revokeObjectURL(fileUrl)
            this.exportOwnerLoading = false
      }).catch((e) => {
                    console.log(e)
                    this.exportOwnerLoading = false
            })
        }*/
    }

    /**
     * 设置导出excel中列的宽度
     */
    private static class CustomizeColumnWidth extends AbstractColumnWidthStyleStrategy {
        @Override
        protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<CellData> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
            Sheet sheet = writeSheetHolder.getSheet();
            sheet.setColumnWidth(cell.getColumnIndex(), 4000);
        }
    }

    /**
     * 设置excel表头、内容样式
     */
    private static HorizontalCellStyleStrategy getHeadAndContentStyle() {
        // 设置表头样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        WriteFont headWriteFont = new WriteFont();
        // 设置表头字体大小
        headWriteFont.setFontHeightInPoints((short) 11);
        headWriteCellStyle.setWriteFont(headWriteFont);

        // 设置表格内容样式
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 设置水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 设置垂直居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }

    /**
     * 获取excel表头
     *
     * @param frontList 表头list
     * @return list
     */
    private List<List<String>> getHead(List<String> frontList) {
        List<List<String>> list = Lists.newArrayList();
        for (String head : frontList) {
            List<String> headList = Lists.newArrayList();
            headList.add(head);
            list.add(headList);
        }
        return list;
    }

    @PostMapping("/exportExcelData")
    @ApiOperation("导出excel数据")
    public void exportExcelData(HttpServletResponse response, @RequestParam String headList, @RequestParam String dataList) throws IOException {
        log.info("===============导出excel数据入参：headList：{}，listData：{}=================", headList, dataList);

        // excel表头list
        List<String> headListData = new ArrayList<>();
        // excel数据list
        List<String> dataLists;
        // excel数据写入list
        List<List<String>> excelList = new ArrayList<>();

        if (!StringUtils.isEmpty(headList)) {
            headListData = JSON.parseArray(headList, String.class);
            log.info("headListData：{}", headListData);
        }

        if (!StringUtils.isEmpty(dataList)) {
            dataLists = JSON.parseArray(dataList, String.class);
            for (String str : dataLists) {
                List<String> data = JSON.parseArray(str, String.class);
                excelList.add(data);
            }
            log.info("excelList：{}", excelList);
        }
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 不设置前端无法从header获取文件名
            response.setHeader("Access-Control-Expose-Headers", "filename");
            // 防止中文乱码
            String fileName = URLEncoder.encode("模板数据.xlsx", "utf-8").replaceAll("\\+", "%20");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
            response.setHeader("filename", fileName);
            // 创建excel工作簿
            EasyExcel.write(response.getOutputStream())
                    .registerWriteHandler(new CustomizeColumnWidth()).registerWriteHandler(getHeadAndContentStyle())
                    .head(getHead(headListData)).autoCloseStream(Boolean.FALSE).sheet("模板数据").doWrite(excelList);
            log.info("导出excel数据完成");
        } catch (Exception e) {
            log.error("导出excel数据失败：", e);
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<>(2);
            map.put("status", "failure");
            map.put("message", "导出excel数据失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }
}
