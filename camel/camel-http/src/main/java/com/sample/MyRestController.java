package com.sample;


import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyRestController {
 

	   @PostMapping("/order")
	    public String postBody(@RequestBody Order order) {
		   System.out.println("Invoked rest with "+order);
		   String note = "Item ordered on "+new java.util.Date().toString();
	       return note;
	    }

	   @GetMapping("/hello")
	    public String postBody(@RequestParam String name) {
	       return "Hello "+name;
	    }
}