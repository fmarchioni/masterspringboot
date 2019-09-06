package com.example.demo;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
@FeignClient("spring-cloud-eureka-client")
public interface HelloServiceClient {
    @GetMapping("/hello")
    String sayHello();
}