package com.example.droolsdemo;


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

	}


}