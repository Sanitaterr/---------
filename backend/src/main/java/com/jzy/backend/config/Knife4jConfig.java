package com.jzy.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/3/18 21:32
 * @Description: TODO
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jzy.backend.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private static final String API_TILE="backend";
    //文档信息配置
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(API_TILE)
                .description("# backend")
                .version("1.0")
                .build();
    }
}
