package com.hao.springbootdemo.controller;

import com.hao.springbootdemo.util.exception.GlobalException;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xu.liang
 * @since 2022/12/15 09:37
 */
@RestController
@RequestMapping("")
@Slf4j
@Api(tags = "File")
public class FileController {


    @PostMapping("/download")
    public void download(@RequestParam("fileName") String fileName, @RequestParam("fileContent") String fileContent, HttpServletResponse response, HttpServletRequest request) {
        File file = null;
        BufferedOutputStream bos = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            // 系统缓存地址
            String filePath = request.getServletContext().getRealPath("/") + fileName + ".vue";
            log.info("下载vue文件路径：" + filePath);

            String name = URLEncoder.encode(fileName + ".vue", "utf-8").replaceAll("\\+", "%20");
            response.setContentType("application/x-msdownload");
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + name);
            response.setHeader("filename", name);
            file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(fileContent.getBytes());
            bos.flush();
            is = new FileInputStream(file);
            os = response.getOutputStream();
            os.write(fileContent.getBytes());
            os.close();
            IOUtils.copy(is, os);

        } catch (Exception e) {
            log.error("下载vue文件异常：", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("输入流关闭异常：", e);
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    log.error("缓冲输出流关闭异常：", e);
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.error("返回输出流关闭异常：", e);
                }
            }
            FileUtils.deleteQuietly(file);
            log.info("删除文件成功");
        }
    }

    @PostMapping("/download2")
    public void download2(@RequestParam("fileName") String fileName, @RequestParam("fileContent") String fileContent, HttpServletResponse response) {

        response.setContentType("application/x-msdownload");
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        OutputStream os = null;
        try {
            String name = URLEncoder.encode(fileName + ".vue", "utf-8").replaceAll("\\+", "%20");
            response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + name);
            response.setHeader("filename", name);
            os = response.getOutputStream();
            os.write(fileContent.getBytes(StandardCharsets.UTF_8));
            os.close();
        } catch (Exception e) {
            log.error("下载文件异常：", e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.error("返回输出流关闭异常：", e);
                }
            }
        }
    }

    @PostMapping("/upload")
    public Map<String, String> upload(@RequestPart MultipartFile file) {
        BufferedReader reader = null;
        StringBuilder fileContent = new StringBuilder();
        try {
            Reader read = new InputStreamReader(file.getInputStream(), "UTF-8");
            reader = new BufferedReader(read);
            String tmpString;
            // 行读取文件里面的内容
            while ((tmpString = reader.readLine()) != null) {
                // 内容追加到fileContent
                fileContent.append(tmpString).append("\n");
            }
            if ("".equals(fileContent.toString())) {
                throw new GlobalException("文件内容为空");
            }
        } catch (IOException e) {
            log.error("上传vue文件解析信息失败", e);
            throw new GlobalException("上传vue文件解析信息失败");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    log.error("关闭流失败", e1);
                }
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("fileContent", fileContent.toString());
        map.put("fileName", file.getOriginalFilename());
        return map;
    }

}
