package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@DisplayName("GET /customers works")
	void testGetUsers_whenValidJWTProvided_returnsUsers() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/json");

		HttpEntity requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<List<Customer>> response = restTemplate.exchange("/customers",
				HttpMethod.GET,
				requestEntity,
				new ParameterizedTypeReference<List<Customer>>() {
				});
		List<Customer> customers = response.getBody();

		// Assert
		assertTrue(response.getStatusCode().is2xxSuccessful(), "HTTP Response status code should be 200");
		assertTrue(customers.size() == 2, "There should be exactly 2 customers in the list");
	}

}