package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MyService {

    private static final Logger logger = LoggerFactory.getLogger(MyService.class);

    @Async("asyncExecutor")
    public void process() {
        // Simulate access to an external system that produces an ID
        UUID randomUUID = UUID.randomUUID();

        // Convert UUID to a string
        String id = randomUUID.toString();

        logger.info("Started processing request id "+id);
        try {
            Thread.sleep(15 * 1000);
            logger.info("Finished processing request id: "+id);
        }
        catch (InterruptedException ie) {
            logger.error("Error in ProcessServiceImpl.process(): {}", ie.getMessage());
        }

    }
}