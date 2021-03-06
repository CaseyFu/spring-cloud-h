package org.casey.cloud.h.account.service.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @ClassName Swagger2Config
 * @Author Fu Kai
 * @Description todo
 * @Date 2020/12/30 13:50
 */

@Configuration
@EnableSwagger2WebMvc
@EnableKnife4j
public class Swagger2Config {
    @Bean(value = "accountApi")
    @Order(value = 1)
    public Docket groupRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.casey.account.service.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo groupApiInfo(){
        return new ApiInfoBuilder()
                .title("cloud-account-service")
                .description("<div style='font-size:14px;color:red;'>cloud-account-service RESTful APIs</div>")
                .termsOfServiceUrl("http://fukai.asia")
                .contact("caseyfu@qq.com")
                .version("1.0.0")
                .build();
    }
}