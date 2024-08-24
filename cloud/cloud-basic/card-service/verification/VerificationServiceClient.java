package org.example.cardservice.verification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Component
public class VerificationServiceClient {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(VerificationServiceClient.class);
	private final RestTemplate restTemplate;

	VerificationServiceClient(@LoadBalanced RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public ResponseEntity<VerificationResult> verify(VerificationApplication verificationApplication) {
		LOGGER.debug("Sending verification request for application placed by user {}", verificationApplication
				.getUserId());
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
				.fromHttpUrl("http://fraud-verifier/cards/verify")
				.queryParam("uuid", verificationApplication.getUserId())
				.queryParam("cardCapacity", verificationApplication.getCardCapacity());
		return restTemplate.getForEntity(uriComponentsBuilder.toUriString(),
				VerificationResult.class);
	}
}
