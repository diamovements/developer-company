package com.example.developer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication(scanBasePackages = {"com.example"})
public class DeveloperApplication {
	public static void main(String[] args) {
		SpringApplication.run(DeveloperApplication.class, args);
	}

}
