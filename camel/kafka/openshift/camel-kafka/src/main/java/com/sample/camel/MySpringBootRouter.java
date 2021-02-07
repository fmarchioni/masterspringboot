package com.sample.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.apache.camel.component.kafka.KafkaConstants;
/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class MySpringBootRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Kafka Producer
        from("file:src/data?noop=true")
                .setHeader(KafkaConstants.KEY, constant("Camel")) // Key of the message
                .to("kafka:stocks?brokers=my-kafka-kafka-bootstrap:9092&groupId=A");

        // Kafka Consumer
        from("kafka:stocks?brokers=my-kafka-kafka-bootstrap:9092&groupId=A")
                .log("Message received from Kafka : ${body}")
                .log("    on the topic ${headers[kafka.TOPIC]}")
                .log("    on the partition ${headers[kafka.PARTITION]}")
                .log("    with the offset ${headers[kafka.OFFSET]}")
                .log("    with the key ${headers[kafka.KEY]}");
    }

}
