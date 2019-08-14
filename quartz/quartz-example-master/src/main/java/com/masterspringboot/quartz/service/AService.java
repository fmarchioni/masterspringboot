package com.masterspringboot.quartz.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AService {

    public void printTime() {
        System.out.println("Hi there ! " + new Date());
    }
}
