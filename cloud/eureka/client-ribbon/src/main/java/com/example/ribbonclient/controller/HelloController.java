package com.example.ribbonclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ribbonclient.service.HelloServiceClient;


@Controller
public class HelloController {
	
	@Autowired
	HelloServiceClient helloServiceClient;
	
	@GetMapping("/hello")
	String sayHello(ModelMap model){
		model.put("message", helloServiceClient.sayHello());
		return "hello";
	}
}
