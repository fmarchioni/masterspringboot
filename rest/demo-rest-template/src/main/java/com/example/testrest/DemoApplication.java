package com.example.testrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public void saveCustomer(Customer customer) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);

		RestTemplate restTemplate = new RestTemplate();

		// Data attached to the request.
		HttpEntity<Customer> requestBody = new HttpEntity<>(customer, headers);

		// Send request with POST method.
		restTemplate.postForObject("http://localhost:8080/save", requestBody, Customer.class);

	}


	public void listCustomers() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);

		ResponseEntity<Customer[]> response = getRestTemplate().exchange("http://localhost:8080/list", //
				HttpMethod.GET, entity, Customer[].class);

		HttpStatus statusCode = response.getStatusCode();
		System.out.println("Response Status Code: " + statusCode);

		// Status Code: 200
		if (statusCode == HttpStatus.OK) {
			// Response Body Data
			Customer[] list = response.getBody();

			if (list != null) {
				for (Customer c : list) {
					System.out.println(c.toString());
				}
			}
		}
	}

	public void run(String... args) {
		Customer c1 = new Customer(1, "john");
		Customer c2 = new Customer(2, "frank");
		saveCustomer(c1);
		saveCustomer(c2);
		listCustomers();
	}

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

	}

}
