package com.hao.springbootdemo.util.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * 生成器配置
 *
 * @author xu.liang
 * @since 2021/10/5 16:38
 */
public class GeneratorTests {

    /**
     * 生成代码的表名
     */
    private static final String TABLE_NAME = "user_info";

    /**
     * 数据库相关配置
     */
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf-8&useSSL=false&nullCatalogMeansCurrent=true&serverTimezone=Asia/Shanghai";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    /**
     * 注释显示的作者
     */
    private static final String AUTHOR = "xu.liang";

    /**
     * 代码生成指定包名
     */
    private static final String PACKAGE_NAME = "com.hao";

    /**
     * 指定代码生成路径
     */
//    private static final String OUTPUT_DIR = "D:\\file\\tmp\\generator\\output";
    private static final String OUTPUT_DIR = "/Users/liang/IdeaProjects/SpringBootDemo/src/main/java/com/hao/springbootdemo/entity";

    /**
     * 模板存放路径
     */
    private static final String TEMPLATE_PATH = "./static/generator/";

    @Test
    public void generate() {
        System.out.println(OUTPUT_DIR);
        FastAutoGenerator.create(new DataSourceConfig.Builder(JDBC_URL, USERNAME, PASSWORD))
                // 全局配置
                .globalConfig(builder -> builder.author(AUTHOR).fileOverride().outputDir(OUTPUT_DIR))
                // 包配置
                .packageConfig(builder -> builder.parent(PACKAGE_NAME))
                // 策略配置
                .strategyConfig(builder -> builder
                        // 指定表名，如不指定，则默认生成所有
//                        .addInclude(TABLE_NAME)
                        .controllerBuilder().enableRestStyle()
                        .serviceBuilder()
                        .entityBuilder()
                        // 指定父类
                        .superClass(PACKAGE_NAME + ".entity.BaseEntity")
                        // 添加父类字段
                        .addSuperEntityColumns("is_deleted", "gmt_create", "gmt_modified", "tenant_id")
                        // 开启AR模式
                        .enableActiveRecord()
                        // ID类型，这里IdType是个枚举
                        .idType(IdType.AUTO)
                        // 表名命名转换，这里是下划线转驼峰
                        .naming(NamingStrategy.underline_to_camel)
                        // 表字段名转换，下划线转驼峰
                        .columnNaming(NamingStrategy.underline_to_camel)
                        // 开启lombok
                        .enableLombok()
                        // 开启链式编程 （与BeanCopier冲突）
                        .enableChainModel())
                // 不生成xml
                .templateConfig(builder -> builder.disable(TemplateType.XML)
                        .controller(TEMPLATE_PATH + "controller.java.vm")
                        .entity(TEMPLATE_PATH + "entity.java.vm")
                        .mapper(TEMPLATE_PATH + "mapper.java.vm")
                        .service(TEMPLATE_PATH + "service.java.vm")
                        .serviceImpl(TEMPLATE_PATH + "serviceImpl.java.vm")
                )
                // 自定义模板位置
                .execute();
    }
}
