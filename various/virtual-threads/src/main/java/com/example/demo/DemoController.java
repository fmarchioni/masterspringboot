package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thread")
public class DemoController {

    @GetMapping("/name")
    public String getThreadName() {
        return Thread.currentThread().toString();
    }

}
