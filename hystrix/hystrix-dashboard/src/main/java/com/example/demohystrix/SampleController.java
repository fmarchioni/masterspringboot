package com.example.demohystrix;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class SampleController
{
    @RequestMapping(value = "/hello")
    @HystrixCommand(fallbackMethod = "planb", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public String hello() throws InterruptedException {
        Thread.sleep(2000);
        return "Hello World";
    }
    private String planb() {
        return "Sorry our Systems are busy! try again later.";
    }
}