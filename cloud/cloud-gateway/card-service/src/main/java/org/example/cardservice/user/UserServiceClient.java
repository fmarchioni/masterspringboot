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
    // #3
	public ResponseEntity<User> registerUser(CardApplicationDto.User userDto) {
		List<String> list = discoveryClient.getServices();
		for (String service: list)
		    System.out.println("Services ----------------------->"+service);

		ServiceInstance instance = discoveryClient.getInstances("proxy")
				.stream().findAny()
				.orElseThrow(() -> new IllegalStateException("Proxy unavailable"));

		System.out.println("Chiamo MicroServizio UserService "+instance.getUri().toString()
				+ "/user-service/registration");
		return restTemplate.postForEntity(instance.getUri().toString()
						+ "/user-service/registration",
				userDto,
				User.class);
	}
}