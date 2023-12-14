package com.example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AsyncControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldReturnResponseInLessThen1Second() {
		long startTime = System.currentTimeMillis();

		ResponseEntity<String> response = restTemplate.
				getForEntity("/async", String.class);

		Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

		long endTime = System.currentTimeMillis();

		assertThat(endTime - startTime).isLessThan(1000);
	}
}