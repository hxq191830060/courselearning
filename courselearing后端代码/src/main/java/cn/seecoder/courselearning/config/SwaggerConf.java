package cn.seecoder.courselearning.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConf {
    @Bean
    public  Docket getDocket(){
        Docket docket=new Docket(DocumentationType.SWAGGER_2);
        ApiInfo apiInfo=getApi();
        docket.select().apis(RequestHandlerSelectors.basePackage("cn")).paths(PathSelectors.any()).build();
        return docket;
    }

    public ApiInfo getApi(){
        Contact contact=new Contact("黄相淇","https://user.qzone.qq.com/907478820/main","907478820@qq.com");
        ApiInfo apiInfo=new ApiInfo(
                "软工二大作业SwaggerApi文档",
                "欢迎团队成员进入文档查看接口",
                "1.0",
                "https://user.qzone.qq.com/907478820/main",
                contact,
                "Apache 2.0","http://www.apache.org/licenses/LICENSE-2.0",//文档链接
                new ArrayList<>()
        );
        return apiInfo;
    }

}
