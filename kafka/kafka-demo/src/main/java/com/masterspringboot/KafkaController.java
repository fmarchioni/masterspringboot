package com.masterspringboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final KafkaProducer producer;

    @Autowired
    KafkaController(KafkaProducer producer) {
        this.producer = producer;
    }

    @GetMapping(value = "/publish/{message}")
    public String sendMessageToKafkaTopic(@PathVariable ("message") String message) {
        this.producer.sendMessage(message);
        return "Message sent! check logs!";
    }
}