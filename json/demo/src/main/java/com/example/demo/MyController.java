package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping("/data")
    public ResponseEntity<Object> getData() {
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/persons")
    public List<Person> getPersons() {
        return List.of(new Person("John","Doe"), new Person("Adam","Smith"));
    }

}
