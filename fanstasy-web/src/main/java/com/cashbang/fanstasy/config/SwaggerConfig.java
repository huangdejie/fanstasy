package com.cashbang.fanstasy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by huangdejie on 2018/8/20 0020.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.cashbang.fanstasy"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建api的基本信息
     *
     * @return
     */
    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("接口文档说明")
                .termsOfServiceUrl("")
                .contact("")
                .version("1.0")
                .build();
    }

}
