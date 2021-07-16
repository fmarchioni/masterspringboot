package com.example.testrest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoController
{


    @RequestMapping("/secure")
    public String echo()
    {
        return "Hello from Spring Boot SSL";
    }


}
