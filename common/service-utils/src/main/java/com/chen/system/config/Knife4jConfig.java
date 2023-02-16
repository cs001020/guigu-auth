package com.chen.system.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * knife4j配置
 *
 * @author CHEN
 * @date 2022/10/22
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {
    @Bean(value = "dockerBean")
    public Docket dockerBean() {
        //指定使用Swagger2规范
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //描述字段支持Markdown语法
                        .description("# TaGao点评")
                        .termsOfServiceUrl("https://tagao.warframe.top/")
                        .contact(new Contact("CH","2","1006596474@qq.com"))
                        .version("1.0")
                        .build())
                //分组名称
                //.groupName("用户服务")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.chen.system.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
