package com.example.demo.demo3_2.actuator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomActuatorConfiguration {

    @Bean
    public HelloWorldEndpoint helloWorldEndpoint() {
        return new HelloWorldEndpoint();
    }

}