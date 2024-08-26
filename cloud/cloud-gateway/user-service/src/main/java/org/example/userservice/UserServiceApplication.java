package org.example.userservice;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.example.userservice.VerificationResult.Status.VERIFICATION_PASSED;

@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	RestTemplateBuilder restTemplateBuilder() {
		return new RestTemplateBuilder();
	}

	@Bean
	Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.timeLimiterConfig(TimeLimiterConfig.custom()
						.timeoutDuration(Duration.ofSeconds(2))
						.build())
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.build());
	}

}

@RestController
@RequestMapping("/registration")
class UserRegistrationController {

	private final UserRegistrationService userRegistrationService;

	UserRegistrationController(UserRegistrationService userRegistrationService) {
		this.userRegistrationService = userRegistrationService;
	}
    // #4
	@PostMapping
	ResponseEntity<User> registerUser(@RequestBody UserDto userDto,
			UriComponentsBuilder uriComponentsBuilder) {

		System.out.println("-------------------> Register user "+userDto);

        User user = userRegistrationService.registerUser(userDto);
		UriComponents uriComponents = uriComponentsBuilder.path("/users/{uuid}")
				.buildAndExpand(user.getUuid());

		System.out.println("-------------------> uriComponents " + uriComponents.toUri());
		return ResponseEntity.created(uriComponents.toUri()).body(user);
	}
}

@Service
class UserRegistrationService {

	User registerUser(UserDto userDto) {
		User user = new User(userDto);
		verifyUser(user);
		return user;
	}
    // #5
	private void verifyUser(User user) {

		System.out.println("Check user age");
		if (user.getAge() > 18)  {
			user.setStatus(User.Status.OK );
		}
		else {
			user.setStatus(User.Status.FRAUD );
		}


}


}