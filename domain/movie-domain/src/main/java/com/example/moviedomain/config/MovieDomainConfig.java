package com.example.moviedomain.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.moviedomain.repository")
@EntityScan(basePackages = "com.example.moviedomain.entity")
@ComponentScan(basePackages = "com.example.moviedomain.config")
public class MovieDomainConfig {
}
