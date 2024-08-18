package com.hao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
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
public class SwaggerBeanConfig implements WebMvcConfigurer {

    // Docket bean definition （方法名不可重复）
    @Bean
    public Docket haoApi() {
        // configuring the API documentation settings
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(Docket.DEFAULT_GROUP_NAME)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hao"))
//                .paths(PathSelectors.ant("/api/*"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo()); // set custom API info
    }

    // setting the API information
    private ApiInfo apiInfo() {

        return new ApiInfoBuilder().title("Demo API") // 网页标签标题
                .description("API documentation for Demo project")
                .version("1.0.0")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }

}
