package com.example.Finomoly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FinomolyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinomolyApplication.class, args);
	}

}
