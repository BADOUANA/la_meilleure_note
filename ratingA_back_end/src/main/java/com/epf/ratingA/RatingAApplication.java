package com.epf.ratingA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ComponentScan(basePackages = "com.epf.ratingA.mappers")
public class RatingAApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingAApplication.class, args);
	}

}
