package com.masterspringboot;

import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class KafkaConsumer {

    private static final Logger logger = Logger.getLogger(KafkaConsumer.class.getName());

    @KafkaListener(topics = "mytopic", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info(String.format("Consumed message -> %s", message));
    }
}