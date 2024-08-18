 package com.hao.config.CrossOrigin;

 import com.hao.config.GlobalConfig;
 import com.hao.config.bean.Cors;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.web.filter.CharacterEncodingFilter;
 import org.springframework.web.servlet.config.annotation.*;

 import javax.annotation.Resource;
 import java.util.List;

 /**
  * @author xu.liang
  * @since 2024/3/1 09:18
  */
 @EnableWebMvc
 @Configuration
 public class WebConfig implements WebMvcConfigurer {

     @Resource
     private CrossOriginIntercept crossOriginIntercept;

     @Resource
     private GlobalConfig globalConfig;

     /**
      * 方法1：在Controller加上@CrossOrigin
      * 方法2：该方法
      *
      * @param registry registry
      */
     @Override
     public void addInterceptors(InterceptorRegistry registry) {
         // 不拦截的uri
         final String[] commonExclude = {"/static/**", "/error", "/files/**", "**"};
         registry.addInterceptor(crossOriginIntercept).addPathPatterns("/**").excludePathPatterns(commonExclude);
     }

     public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("/**")
                 .addResourceLocations("classpath:/static/");
         registry.addResourceHandler("swagger-ui.html")
                 .addResourceLocations("classpath:/META-INF/resources/");
         registry.addResourceHandler("/webjars/**")
                 .addResourceLocations("classpath:/META-INF/resources/webjars/");
         registry.addResourceHandler("doc.html")
                 .addResourceLocations("classpath:/META-INF/resources/");
     }

     public void addCorsMappings(CorsRegistry registry) {
         Cors cors = globalConfig.getCors();
         CorsRegistration corsRegistration = registry.addMapping(cors.getMapping());
         corsRegistration
                 .allowedOrigins(cors.getAllowedOrigins())
                 .allowCredentials(cors.getAllowCredentials())
                 .allowedMethods(cors.getAllowedMethods().toArray(new String[0]))
                 .maxAge(cors.getMaxAge());
         List<String> exposedHeaders = cors.getExposedHeaders();
         if (exposedHeaders != null && !exposedHeaders.isEmpty()) {
             corsRegistration.exposedHeaders(exposedHeaders.toArray(new String[0]));
         }
     }

     @Bean
     public CharacterEncodingFilter characterEncodingFilter() {
         CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
         characterEncodingFilter.setEncoding("UTF-8");
         characterEncodingFilter.setForceEncoding(true);
         return characterEncodingFilter;
     }

     /**
      * 解决swagger死循环问题
      * 视具体情况用,若控制台报错 No mapping for GET /aifruit/null/swagger-resources/configuration/security
      * 则使用下面代码
      * 下面这几个地址会一直轮询，改为重定向
      * /null/swagger-resources/configuration/ui
      * /null/swagger-resources/configuration/security
      * /null/swagger-resources
      */
     // @Override
     // public void addViewControllers(ViewControllerRegistry registry) {
     //     registry.addRedirectViewController("/null/api-docs",
     //             "/api-docs").setKeepQueryParams(true);
     //     registry.addRedirectViewController("/null/swagger-resources/configuration/ui",
     //             "/swagger-resources/configuration/ui");
     //     registry.addRedirectViewController("/null/swagger-resources/configuration/security",
     //             "/swagger-resources/configuration/security");
     //     registry.addRedirectViewController("/null/swagger-resources", "/swagger-resources");
     // }

 }
