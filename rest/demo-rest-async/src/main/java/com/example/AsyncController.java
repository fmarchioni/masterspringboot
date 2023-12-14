package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
    public class AsyncController {
    
        @Autowired
        private MyService processService;
        
        @RequestMapping(value = "async", method = RequestMethod.GET)
            public ResponseEntity<Map<String, String>> async() {
                processService.process();
                Map<String, String> response = new HashMap<>();
                response.put("message", "Started processing your request");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
    }
 

