package com.example.demo.demo3_2.actuator;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

@Endpoint(id = "helloworld")
public class HelloWorldEndpoint {
/*
    @ReadOperation
    public String helloWorld() {
        return "Hello World";
    }s
 */
    @ReadOperation
    public String helloName(String name) {
        return "Hello " + name;
    }

    // Path variable
    @ReadOperation
    public String helloNameSelector(@Selector String name) {
        return "Hello " + name;
    }

    @WriteOperation
    public String helloNameBody(String name) {
        return "Hello " + name;
    }

    @DeleteOperation
    public String goodbyeNameParam(String name) {
        return "Goodbye " + name;
    }

    @DeleteOperation
    public String goodbyeNameSelector(@Selector String name) {
        return "Goodbye " + name;
    }

}