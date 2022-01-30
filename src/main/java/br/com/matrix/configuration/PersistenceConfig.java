package br.com.matrix.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "br.com.matrix.repository")
@Configuration
public class PersistenceConfig { 
}