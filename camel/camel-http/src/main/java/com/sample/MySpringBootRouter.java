package com.sample;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class MySpringBootRouter extends RouteBuilder {

    @Override
    public void configure() {
    	from("timer://test1?period=2000")
        .process(exchange -> exchange.getIn().setBody(new Order(UUID.randomUUID().toString(), ThreadLocalRandom.current().nextInt(1, 10))))
        .marshal().json(JsonLibrary.Gson)
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .to("http://localhost:8080/order")
        .process(exchange -> log.info("HTTP POST response is: {}", exchange.getIn().getBody(String.class)));

   
    	
    	from("timer://test2?period=1000")
        .process(exchange -> exchange.getIn().setBody(simple(null)))
        .setHeader(Exchange.HTTP_METHOD, constant("GET"))
        .setHeader(Exchange.HTTP_QUERY, constant("name=Frank"))
        .to("http://localhost:8080/hello")
        .process(exchange -> log.info("HTTP GET response is: {}", exchange.getIn().getBody(String.class)));
    }
 

}
