package com.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.education.config.property.EducationApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(EducationApiProperty.class)
public class EducationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducationApiApplication.class, args);
	}
}
