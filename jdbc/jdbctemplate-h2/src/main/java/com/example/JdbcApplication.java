package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(JdbcApplication.class);
	@Autowired PersonService service;

	public static void main(String[] args) {
		SpringApplication.run(JdbcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

	}
}