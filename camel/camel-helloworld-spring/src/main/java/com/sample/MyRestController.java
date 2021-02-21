package com.sample;


import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyRestController {


    @Autowired
    private ProducerTemplate template;

    @RequestMapping("/hello")
    public String sayHello(@RequestParam(defaultValue = "frank") String name) {
        return template.requestBody("direct:sayHello", name).toString();
    }


}