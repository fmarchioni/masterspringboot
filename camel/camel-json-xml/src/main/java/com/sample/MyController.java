package com.sample;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    private ProducerTemplate producerTemplate;
    @Autowired
    ConsumerTemplate consumerTemplate;

    @RequestMapping(path = "/xml", 
    method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE,
    consumes = MediaType.APPLICATION_XML_VALUE)
 
    public  String xmlToJson(@RequestBody Customer c) {
        
        System.out.println("Received "+c);
        producerTemplate.sendBody("direct:xmlIn", c);
        return consumerTemplate.receiveBody("seda:output", String.class);
        
    }
}
