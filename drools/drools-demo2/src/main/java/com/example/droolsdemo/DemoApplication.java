package com.example.droolsdemo;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	CustomerService service;

	@Bean
	public KieContainer kieContainer() {
		return KieServices.Factory.get().getKieClasspathContainer();
	}

	public void run(String... args) {
		Server s1 = new Server("rhel7",2,1024,2048);
		service.addServerFacts(s1);
		Server s2 = new Server("rhel8",2,2048,4096);
		service.addServerFacts(s2);
		System.out.println("Server is valid: " +s1.isValid());
		System.out.println("Server is valid: " +s2.isValid());
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}
}