package com.springbatchexample.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbatchexample.model.Person;

public class CustomProcessor implements ItemProcessor<String, String> {    

    @Override
    public String process(String text) throws Exception {
        // logger.info("Custom Processor {}", item);

        Person person = new Person(text, (int) (Math.random() * 100) + 1);

        ObjectMapper objectMapper = new ObjectMapper();

        // Convert Person record to JSON string
        String json = objectMapper.writeValueAsString(person);

        return json;

    }
}