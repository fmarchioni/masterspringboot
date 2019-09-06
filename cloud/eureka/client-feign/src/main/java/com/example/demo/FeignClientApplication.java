/*
spring init -n feign-client -dcloud-eureka,cloud-feign,web,thymeleaf
 */


package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FeignClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(FeignClientApplication.class, args);
	}
}
