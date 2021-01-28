package com.example.droolsdemo;

//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class DemoApplicationTest {


	@Autowired
	CustomerService service;

	@Test
	public void testDiscount() {
		Customer customer1 = new Customer("Frank");
		customer1.setAge(4);

		Customer customer2 = new Customer("John");
		customer2.setAge(1);

		service.insertCustomer(customer1);
		service.insertCustomer(customer2);

		assertEquals(25, customer1.getDiscount());
		assertEquals(15, customer2.getDiscount());
	}


}