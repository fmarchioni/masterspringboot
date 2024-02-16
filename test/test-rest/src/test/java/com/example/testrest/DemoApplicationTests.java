package com.example.testrest;

import org.hamcrest.Matchers;

import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DemoApplicationTests {

	@Test

	public void testCustomerList() {

		Customer newCustomer = new Customer();
		newCustomer.setName("Mary Smith");

		RestAssured.given()
				.baseUri("http://localhost:8080")
				.contentType(ContentType.JSON)
				.body(newCustomer)
				.when()
				.post("/add")
				.then()
				.statusCode(201)
				.body("id", equalTo(1))
				.body("name", equalTo("Mary Smith"));

		Response response = RestAssured.given()
				.when()
				.get("http://localhost:8080/list")
				.then()
				.assertThat()
				.statusCode(200)
				.extract()
				.response();
		// Option 1: extract the JSON response as String		
		String responseBody = response.getBody().asString();
		// Option 2: extract the JsonPath from Response
		JsonPath jsonPath = response.jsonPath();

		List<Customer> customers = jsonPath.getList("$", Customer.class);
		Customer myPojo = customers.get(0);

		assertEquals(myPojo.getName(), newCustomer.getName());

		get("http://localhost:8080/one/1")
				.then()
				.assertThat()
				.statusCode(200)
				.body("name", Matchers.equalTo("Mary Smith"));

	}

}
