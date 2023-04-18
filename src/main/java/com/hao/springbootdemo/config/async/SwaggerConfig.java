package com.hao.springbootdemo.config.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger配置
 *
 * @author xu.liang
 * @since 2023/04/13 15:43
 */
@Configuration
public class SwaggerConfig {

    // Docket bean definition （方法名不可重复）
    @Bean
    public Docket api() {
        // configuring the API documentation settings
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hao.springbootdemo"))
//                .paths(PathSelectors.ant("/api/*"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo()); // set custom API info
    }

    // setting the API information
    private ApiInfo apiInfo() {

        return new ApiInfoBuilder().title("Demo API")
                .description("API documentation for Demo project")
                .version("1.0.0")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }

}
