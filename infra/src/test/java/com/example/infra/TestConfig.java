package com.example.infra;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.infra.screening.repository")
@EntityScan(basePackages = "com.example.domain.screening.entity")
public class TestConfig {
}