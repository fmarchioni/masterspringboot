package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyServiceTest {

    @Autowired
    private MyService myService;

    @Test
    public void testGetMessage() {
        String message = myService.getMessage();
        assertEquals("Hello, world!", message);
    }
}