package com.hao.controller;

// import cn.hutool.core.io.resource.ClassPathResource;

import cn.hutool.core.io.resource.InputStreamResource;
import cn.hutool.json.JSONUtil;
import com.hao.util.exception.GlobalException;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
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

    /**
     * @see <a href="https://www.yuque.com/chuinixiongkou/gc-starter/zyt5uil155698kna"></a>
     * 返回类型必须为void
     */
    @PostMapping("/download")
    public void download(@RequestParam("fileName") String fileName, @RequestParam("fileContent") String fileContent, HttpServletResponse response) {

        response.setContentType("application/x-msdownload");
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        OutputStream os = null;
        try {
            String name = URLEncoder.encode(fileName + ".txt", "utf-8").replaceAll("\\+", "%20");
            response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + name);
            // 不设置前端无法从header获取文件名
            response.setHeader("Access-Control-Expose-Headers", "filename");
            response.setHeader("filename", name);
            os = response.getOutputStream();
            os.write(fileContent.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("下载文件异常：", e);
            throw new GlobalException("下载文件失败");
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

    @PostMapping("/download2")
    public void download2(@RequestParam("fileName") String fileName, @RequestParam("fileContent") String fileContent, HttpServletResponse response, HttpServletRequest request) {
        File file = null;
        BufferedOutputStream bos = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            // 临时文件缓存地址
            String filePath = request.getServletContext().getRealPath("/") + fileName + ".txt";
            log.info("下载文件路径：" + filePath);

            // File tempFile = File.createTempFile(UUID.randomUUID().toString(), ".txt");
            // System.out.println("tempXlsx.getName() = " + tempFile.getName());
            // System.out.println("tempXlsx.getPath() = " + tempFile.getPath());

            String name = URLEncoder.encode(fileName + ".txt", "utf-8").replaceAll("\\+", "%20");
            response.setContentType("application/x-msdownload");
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + name);
            // 不设置前端无法从header获取文件名
            response.setHeader("Access-Control-Expose-Headers", "filename");
            response.setHeader("filename", name);
            file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(fileContent.getBytes(StandardCharsets.UTF_8));
            bos.flush();
            is = new FileInputStream(file);
            os = response.getOutputStream();
            // 或者用下一行代码
            os.write(fileContent.getBytes(StandardCharsets.UTF_8));
            // IOUtils.copy(is, os);

        } catch (Exception e) {
            log.error("下载文件异常：", e);
        } finally {
            IOUtils.closeQuietly(bos, is, os);
            FileUtils.deleteQuietly(file);
            log.info("删除文件成功");
        }
    }

    @PostMapping("/download3")
    public void download3(@RequestParam("fileName") String fileName, @RequestParam("fileContent") String fileContent, HttpServletResponse response) {
        File file;
        InputStream is = null;
        OutputStream os = null;
        try {
            // 需要该目录下存在该文件
            String filePath = "/Users/liang/Desktop/" + fileName + ".txt";
            log.info("下载文件路径：" + filePath);

            String name = URLEncoder.encode(fileName + ".txt", "utf-8").replaceAll("\\+", "%20");
            response.setContentType("application/x-msdownload");
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + name);
            // 不设置前端无法从header获取文件名
            response.setHeader("Access-Control-Expose-Headers", "filename");
            response.setHeader("filename", name);
            file = new File(filePath);
            is = new FileInputStream(file);
            os = response.getOutputStream();
            os.write(fileContent.getBytes(StandardCharsets.UTF_8));

        } catch (Exception e) {
            log.error("下载vue文件异常：", e);
        } finally {
            IOUtils.closeQuietly(is, os);
        }
    }

    @PostMapping("/download4")
    public void download4() {
        // 最简单方法导出文件，后端返回数据给前端，然后前端执行下面的代码即可(vue前端）

     /*
        import FileSaver from 'file-saver'
        // 将json转换成字符串
          const blob = new Blob([testData], { type: '' })
        FileSaver.saveAs(blob, fileName + '.json')
        this.$message({
                message: '导出成功',
                type: 'success'
          })*/
    }

    @PostMapping("/upload")
    public Map<String, String> upload(@RequestPart MultipartFile file) {
        BufferedReader reader = null;
        StringBuilder fileContent = new StringBuilder();
        try {
            Reader read = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
            reader = new BufferedReader(read);
            String tmpStr;
            // 行读取文件内容
            while ((tmpStr = reader.readLine()) != null) {
                fileContent.append(tmpStr).append("\n");
            }
            String data = fileContent.toString();
            if ("".equals(data)) {
                throw new GlobalException("文件内容为空");
            }
            // 判断是否Json字符串
            if (!JSONUtil.isJson(data)) {
                // throw new GlobalException("数据格式异常，请检查重试");
                log.error("数据格式异常，请检查重试");
            }
        } catch (IOException e) {
            log.error("上传失败", e);
            throw new GlobalException("上传失败");
        } finally {
            IOUtils.closeQuietly(reader);
        }
        Map<String, String> map = new HashMap<>(16);
        map.put("fileContent", fileContent.toString());
        map.put("fileName", file.getOriginalFilename());
        return map;
    }

    @PostMapping("/upload2")
    @SneakyThrows
    public Map<String, String> upload2(@RequestPart MultipartFile file) {
        InputStreamResource inputStreamResource = new InputStreamResource(file.getInputStream());
        String data = inputStreamResource.readUtf8Str();

        // 判断是否Json字符串
        if (!JSONUtil.isJson(data)) {
            // throw new GlobalException("数据格式异常，请检查重试");
            log.error("数据格式异常，请检查重试");
        }
        Map<String, String> map = new HashMap<>(16);
        map.put("fileContent", data);
        map.put("fileName", file.getOriginalFilename());
        return map;
    }

    @PostMapping("/readResourceFile")
    @SneakyThrows
    // @RequestLine() // feign
    public Map<String, String> readResourceFile() {
        // spring工具包
        ClassPathResource classPathResource = new ClassPathResource("vue模板.vue");
        InputStream is = classPathResource.getInputStream();

        // hutool工具包 import cn.hutool.core.io.resource.ClassPathResource;
        // ClassPathResource classPathResource = new ClassPathResource("vue模板.vue");
        // InputStream is = classPathResource.getStream();

        // FileInputStream is = new FileInputStream("src/main/resources/vue模板.vue");
        InputStreamResource inputStreamResource = new InputStreamResource(is);
        String data = inputStreamResource.readUtf8Str();

        // FileOutputStream fos = new FileOutputStream("src/main/resources/vue模板1.vue");
        // IOUtils.copy(is, fos);
        // fos.write(data.getBytes(StandardCharsets.UTF_8));

        // 判断是否Json字符串
        if (!JSONUtil.isJson(data)) {
            // throw new GlobalException("数据格式异常，请检查重试");
            log.error("数据格式异常，请检查重试");
        }
        Map<String, String> map = new HashMap<>(16);
        map.put("fileContent", data);
        return map;
    }

    @PostMapping("/downloadRemoteFile")
    public String downloadFile(String filename, String inputPath, final HttpServletRequest request, final HttpServletResponse response) {
        // 实际路径
        String downLoadPath = "http://212.212.212.212:9999/XXXX/p1eivgpi8ebnj1h8c1307p4es594.rar";
        byte[] buff = new byte[4096];
        BufferedInputStream bis = null;
        OutputStream bos;
        try {
            response.setHeader("content-type", "application/x-msdownload");
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(filename.getBytes("GBK"), "ISO8859-1"));
            bos = response.getOutputStream();
            bis = new BufferedInputStream(new URL(downLoadPath).openConnection().getInputStream());
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (IOException e) {
            log.error("下载异常：", e);
        } finally {
            IOUtils.closeQuietly(bis);
        }
        // 实现原理：读取流输出到本地页面，返回值为null就会弹出页面
        return null;
    }

}
