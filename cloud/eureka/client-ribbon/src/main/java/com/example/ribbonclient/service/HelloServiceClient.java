/**
 * 
 */
package com.example.ribbonclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class HelloServiceClient {
	
	@Autowired
	@LoadBalanced
	RestTemplate restTemplate;
	
	public String sayHello(){
		return restTemplate.getForObject("http://SPRING-CLOUD-EUREKA-CLIENT/hello", String.class);
	}
}
