package com.example.testrest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Value;
@RestController
public class SampleController
{
    @Value("${app.title}")
    private String appTitle;

    @Value("${app.description}")
    private String appDescription;


   /*
    public void setCustomerRepository(CustomerRepository repository){
        this.repository = repository;
    }
    */
    @RequestMapping("/hello")
    public String hello()
    {
        return "Hello from "+appTitle + " " +appDescription;
    }


}
