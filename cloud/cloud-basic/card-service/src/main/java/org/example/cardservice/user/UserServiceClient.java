package org.example.cardservice.user;

import org.example.cardservice.application.CardApplicationDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class UserServiceClient {

	private final RestTemplate restTemplate;
	private final DiscoveryClient discoveryClient;


	UserServiceClient(@Qualifier("restTemplate") RestTemplate restTemplate,
			DiscoveryClient discoveryClient) {
		this.restTemplate = restTemplate;
		this.discoveryClient = discoveryClient;
	}

	public ResponseEntity<User> registerUser(CardApplicationDto.User userDto) {

		ServiceInstance instance = discoveryClient.getInstances("user-service")
				.stream().findAny()
				.orElseThrow(() -> new IllegalStateException("user-service unavailable"));

		return restTemplate.postForEntity(instance.getUri().toString()
						+ "/registration",
				userDto,
				User.class);
	}
}