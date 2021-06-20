package com.sample.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code 
     */
    @Override
    public void configure() throws Exception {

        // Kafka Producer using Message key
        from("file:src/data?noop=true")
                 .setHeader(KafkaConstants.KEY, constant("Camel")) 
                .to("kafka:myTopic?brokers=localhost:9092");

        // Kafka Consumer
        from("kafka:myTopic?brokers=localhost:9092")
                .log("Message received from Kafka : ${body}")
                .log("    on the topic ${headers[kafka.TOPIC]}")
                .log("    on the partition ${headers[kafka.PARTITION]}")
                .log("    with the offset ${headers[kafka.OFFSET]}")
                .log("    with the key ${headers[kafka.KEY]}");
    }

}





