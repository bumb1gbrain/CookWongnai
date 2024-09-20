package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.SpringVersion;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example.demo")
public class CwnFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CwnFrontendApplication.class, args);
		System.out.println("Sprint framework version : " + SpringVersion.getVersion());
		System.out.println("Spring boot version : " + SpringBootVersion.getVersion());
	}

}
