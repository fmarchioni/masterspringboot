package com.masterspringboot;

import com.masterspringboot.service.MyBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomApplicationStarter implements CommandLineRunner {

    @Autowired
    MyBannerService service;

    public static void main(String[] args) {

        SpringApplication.run(CustomApplicationStarter.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        service.hello("Hello");
    }
}
