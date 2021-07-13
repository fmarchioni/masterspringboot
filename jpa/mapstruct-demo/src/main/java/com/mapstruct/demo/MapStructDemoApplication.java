package com.mapstruct.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mapstruct.demo.controllers.CustomerController;

@SpringBootApplication
@EnableJpaRepositories("com.mapstruct.demo.repositories")
@EntityScan(basePackages = { "com.mapstruct.demo.entities" })

public class MapStructDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapStructDemoApplication.class, args);
	}

}
