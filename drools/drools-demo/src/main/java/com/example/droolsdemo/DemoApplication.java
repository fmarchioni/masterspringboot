package com.example.droolsdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	CustomerService service;

	public void run(String... args) {
		Customer customer1 = new Customer("Frank");
		customer1.setAge(4);

		Customer customer2 = new Customer("John");
		customer2.setAge(1);

		service.insertCustomer(customer1);
		service.insertCustomer(customer2);

		System.out.println("Allowed discount John: " +customer1.getDiscount());
		System.out.println("Allowed discount Frank: " +customer2.getDiscount());
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}
}