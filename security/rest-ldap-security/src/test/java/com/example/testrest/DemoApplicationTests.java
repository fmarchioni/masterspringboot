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


	@Autowired
	private TestRestTemplate restTemplate;
	@Test
	public void testCustomerList() throws Exception {

		ResponseEntity<String> response = restTemplate
				.withBasicAuth("einstein", "password")
				.getForEntity("/", String.class);

		printJSON(response);

		assertEquals(HttpStatus.OK, response.getStatusCode());




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
