package org.jesuitasrioja.ProyectoCovid.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigurationBasic {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("org.jesuitasrioja.Enfermeria")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}
	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Proyecto Final covid")
				.description("Proyecto final sobre api de covid jesuitas 2021").version("0.0")
				.contact(new Contact("Pablo", "url", "pablosnf.99@gmail.com")).build();
	}
}
