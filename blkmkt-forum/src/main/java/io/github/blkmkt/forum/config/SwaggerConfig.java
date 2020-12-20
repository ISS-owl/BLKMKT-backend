package io.github.blkmkt.forum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket docket() {
        ApiSelectorBuilder builder = new Docket(DocumentationType.SWAGGER_2)    // 定义 Swagger 2.0 规范
                .apiInfo(apiInfo())                                                    // 定义全局描述信息
                .tags(
                        new Tag("文章", "所有关于文章的内容"),
                        new Tag("回复", "所有关于回复的内容")
                )
                .select();

        builder.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class));    // 指定只扫描 @RestController 注解的 Controller 的 API

        return builder.build()
                .ignoredParameterTypes(RequestAttribute.class)    // 排除扫描 @RequestAttribute 注解
                .ignoredParameterTypes(Errors.class);                        // 排除扫描 @Errors 注解
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("论坛API文档")                        // 定义文档标题
                .description("这是一篇关于论坛的API文档")    // 定义文档描述
                .termsOfServiceUrl("http://swagger.io/terms/")                        // 定义服务条款
                .contact(new Contact("jerrywyn", "https://github.com/jerrywyn", "414085716@qq.com"))    // 定义联系信息
                .license("GPL-v3.0")        // 定义 License
                .licenseUrl("https://www.gnu.org/licenses/gpl-3.0.html")        // 定义 License URL
                .version("1.0.0")                    // 定义 API 版本
                .build();
    }
}
