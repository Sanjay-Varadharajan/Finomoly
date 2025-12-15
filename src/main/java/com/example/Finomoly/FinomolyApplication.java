package com.example.Finomoly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaAuditing
public class FinomolyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinomolyApplication.class, args);
	}


	@Bean
	public WebMvcConfigurer cors() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://127.0.0.1:5500")
						.allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}
}