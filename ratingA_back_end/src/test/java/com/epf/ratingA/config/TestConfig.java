package com.epf.ratingA.config;

import com.epf.ratingA.services.FilmServiceTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FilmServiceTest.class) // Remplace ApplicationConfig par le nom de ta classe principale de configuration
public class TestConfig {
}
