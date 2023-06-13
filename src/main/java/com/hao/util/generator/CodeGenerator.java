package com.hao.util.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;

/**
 * @author xu.liang
 * @since 2023/4/22 14:59
 */
public class CodeGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create("url", "username", "password")
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？")))
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？")))
                // 策略配置
                .strategyConfig(builder -> builder.addInclude("t_simple"))
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker 或 Enjoy
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                   .templateEngine(new EnjoyTemplateEngine())
                 */
                .execute();



    }


}
