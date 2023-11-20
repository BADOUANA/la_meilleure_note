package com.epf.ratingA.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.epf.ratingA.services", "com.epf.ratingA.mappers"})
public class AppConfig {
}
