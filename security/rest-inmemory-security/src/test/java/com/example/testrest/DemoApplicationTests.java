package com.example.testrest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.DatatypeConverter;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DemoApplicationTests {
	private static final ObjectMapper om = new ObjectMapper();

	//@WithMockUser is not working with TestRestTemplate
	@Autowired
	private TestRestTemplate restTemplate;
	@Test
	public void testCustomerList() throws Exception {

		ResponseEntity<String> response = restTemplate
				.withBasicAuth("user", "password")
				.getForEntity("/", String.class);

		printJSON(response);

		//Verify user is authorized and content is JSON
		assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Customer c = new Customer(3,"Adam");

		ResponseEntity<String> result = restTemplate
				.withBasicAuth("user", "password")
				.postForEntity("/", c, String.class);

		//Verify user is unauthorized
		Assert.assertEquals(403, result.getStatusCodeValue());

		//Verify user is authorized
		result = restTemplate
				.withBasicAuth("admin", "password")
				.postForEntity("/", c, String.class);

		//Verify request succeed
		Assert.assertEquals(201, result.getStatusCodeValue());


	}

	@Test
	public void testCustomerList2() throws Exception {

		String username = "user";
		String password = "password";


		String url = "http://localhost:8080";

		String authorizationHeader = "Basic " + DatatypeConverter.printBase64Binary((username + ":" + password).getBytes());

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		requestHeaders.add("Authorization", authorizationHeader);

		ResponseEntity<String> response = restTemplate
				.withBasicAuth("user", "password")
				.getForEntity("/", String.class);



		HttpEntity<String> requestEntity = new HttpEntity<>(addUserRequest, requestHeaders);

		ResponseEntity<String> responseEntity = restTemplate.exchange(
				url,
				HttpMethod.GET,
				requestEntity,
				String.class
		);

		FindUserResponse findUserResponse = responseEntity.getBody();
		return findUserResponse;

		ResponseEntity<String> response = restTemplate
				.withBasicAuth("user", "password")
				.getForEntity("/", String.class);

		printJSON(response);

		//Verify user is authorized and content is JSON
		assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Customer c = new Customer(3,"Adam");

		ResponseEntity<String> result = restTemplate
				.withBasicAuth("user", "password")
				.postForEntity("/", c, String.class);

		//Verify user is unauthorized
		Assert.assertEquals(403, result.getStatusCodeValue());

		//Verify user is authorized
		result = restTemplate
				.withBasicAuth("admin", "password")
				.postForEntity("/", c, String.class);

		//Verify request succeed
		Assert.assertEquals(201, result.getStatusCodeValue());


	}
	private static void printJSON(Object object) {
		String result;
		try {
			result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
			System.out.println(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
