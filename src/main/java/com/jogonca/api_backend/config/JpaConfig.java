package com.jogonca.api_backend.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.jogonca.api_backend.repositories")
@EntityScan(basePackages = "com.jogonca.api_backend.models")
public class JpaConfig {
}
