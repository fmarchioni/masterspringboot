package com.masterspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

    @Service
    public class KafkaProducer {

        private static final Logger logger = Logger.getLogger(KafkaProducer.class.getName());

        private static final String TOPIC = "mytopic";

        @Autowired
        private KafkaTemplate<String, String> kafkaTemplate;

        public void sendMessage(String message) {
            logger.info(String.format("Sending message -> %s", message));
            this.kafkaTemplate.send(TOPIC, message);
        }
}

