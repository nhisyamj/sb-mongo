package com.nhisyamj.springboottemplate;

import com.nhisyamj.springboottemplate.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class Application {

	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
		return factory -> factory.setContextPath(Constant.BASE_PATH);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		log.info("SwaggerUI now running on http://localhost:8080{}/swagger-ui.html", Constant.BASE_PATH);
	}

}
