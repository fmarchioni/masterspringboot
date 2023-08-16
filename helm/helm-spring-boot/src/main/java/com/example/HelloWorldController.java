package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

 
@RestController
public class HelloWorldController {

    @Value("${VAR1:default1}")
    private String var1;

    @Value("${VAR2:default2}")
    private String var2;
    
    @GetMapping("/ping")
    public String hello() {
        return "Hello world. Here are some variables: "+var1+ " - "+var2;
    }
}