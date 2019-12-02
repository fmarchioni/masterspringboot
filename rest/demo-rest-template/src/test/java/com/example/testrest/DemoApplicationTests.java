package com.example.testrest;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void testCustomerList() {

		get("http://localhost:8080/list")
				.then()
				.assertThat()
				.statusCode(200)
				.body("size()", is(2));


		get("http://localhost:8080/one/0")
				.then()
				.assertThat()
				.statusCode(200)
				.body("name", Matchers.equalTo("frank"));

		get("http://localhost:8080/one/1")
				.then()
				.assertThat()
				.statusCode(200)
				.body("name", Matchers.equalTo("john"));

	}

}
