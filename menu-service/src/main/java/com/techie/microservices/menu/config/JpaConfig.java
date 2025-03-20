package com.techie.microservices.menu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "om.techie.microservices.product.repository")
public class JpaConfig {
}
