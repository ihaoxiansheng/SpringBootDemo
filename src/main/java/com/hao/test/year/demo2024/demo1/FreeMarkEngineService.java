package com.hao.test.year.demo2024.demo1;


import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * FrameMark 模板引擎ftl渲染服务
 *
 * @author xu.liang
 * @since 2024/1/16 16:22
 */
@Slf4j
@Service
public class FreeMarkEngineService {

    /**
     * 当前模板临时KEY
     */
    private static final String CURR_TEMPLATE_KEY = "currentTemplate";

    /**
     * 默认模板位置
     */
    private static final String DEFAULT_TEMPLATE_PATH = "/templates";

    private static final String UTF8 = "UTF-8";

    /**
     * 渲染模板
     *
     * @param templateContent 模板配置内容
     * @param vars            变量 *需要注意模板引擎渲染变量必须全 - 跟模板匹配*
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public String getContent(String templateContent, Map<String, Object> vars) throws IOException, TemplateException {
        if (StringUtils.isBlank(templateContent)) {
            return null;
        }
        if (MapUtils.isEmpty(vars)) {
            return templateContent;
        }
        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        templateLoader.putTemplate(CURR_TEMPLATE_KEY, templateContent);
        cfg.setTemplateLoader(templateLoader);
        Template template = cfg.getTemplate(CURR_TEMPLATE_KEY, UTF8);
        return this.processBuildTemplate(template, vars);
    }

    /**
     * 渲染模板
     *
     * @param templateName 模板配置名称
     * @param vars         变量
     * @return
     * @throws TemplateException
     * @throws IOException
     */
    public String getContentByTemplate(String templateName, Map<String, Object> vars)
            throws TemplateException, IOException {

        return this.getContentByTemplate(templateName, vars, DEFAULT_TEMPLATE_PATH);
    }

    /**
     * 渲染模板
     *
     * @param templateName 模板配置名称
     * @param vars         变量
     * @param path         模板位置 可以使用重载的无path的方法，默认path:/templates
     * @return
     * @throws TemplateException
     * @throws IOException
     */
    public String getContentByTemplate(String templateName, Map<String, Object> vars, String path)
            throws TemplateException, IOException {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        ClassTemplateLoader templateLoader = new ClassTemplateLoader(
                FreeMarkEngineService.class.getClassLoader(), path);
        cfg.setTemplateLoader(templateLoader);
        cfg.setDefaultEncoding(UTF8);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        Template template = cfg.getTemplate(templateName);
        return this.processBuildTemplate(template, vars);
    }

    /**
     * 渲染
     *
     * @param template
     * @param vars
     * @return
     * @throws TemplateException
     * @throws IOException
     */
    private String processBuildTemplate(Template template, Map<String, Object> vars)
            throws TemplateException, IOException {

        StringWriter writer = new StringWriter();
        template.process(vars, writer);
        writer.flush();
        writer.close();
        return writer.getBuffer().toString();
    }

}

