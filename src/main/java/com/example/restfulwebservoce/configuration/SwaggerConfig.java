package com.example.restfulwebservoce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

    private static final Contact DEFAULT_CONTACT = new Contact("SpringBoot with Swagger"
            ,"https://github.com/temp","temp@email.com");

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
            Arrays.asList("application/json", "applicationm/xml"));



    @Bean
    public Docket api(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(getApiInfo())
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }

    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title("API")
                .description("SpringBoot With Swagger")
                .contact(DEFAULT_CONTACT)
                .version("1.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}
