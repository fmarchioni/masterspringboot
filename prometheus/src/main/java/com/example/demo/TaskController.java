package com.example.demo;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TaskController {


    private final MeterRegistry registry;

    /**
     * We inject the MeterRegistry into this class
     */
    public TaskController(MeterRegistry registry) {
        this.registry = registry;
    }

    /**
     * The @Timed annotation adds timing support, so we can see how long
     * it takes to execute in Prometheus
     * percentiles
     */
    @GetMapping("/task")
    @Timed(value = "task.time", description = "Time taken to return greeting",
            percentiles = {0.5, 0.90})
    public String greeting() {
        registry.counter("task.counter").increment();

        // Simulating a time-consuming task
        try {
            Thread.sleep(new java.util.Random().nextInt(1000) + 1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Task interrupted";
        }
        return "Task completed!";

    }

}




