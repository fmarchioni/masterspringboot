package com.springbatchexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 

@SpringBootApplication
//@ComponentScan(basePackages = "com.springbatchexample.*")
public class SpringMain {
    public static void main(String[] args) {

        SpringApplication.run(SpringMain.class, args);
    }
}
