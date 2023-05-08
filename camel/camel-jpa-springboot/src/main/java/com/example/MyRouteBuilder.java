package com.example;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("file:src/main/resources/data?noop=true")
        .unmarshal().json(JsonLibrary.Jackson, Operation.class)
        .log("Found document: ${body}")
        .to("jpa:com.example.Operation?persistenceUnit=postgresql&flushOnSend=true");



        from("jpa:com.example.Operation?persistenceUnit=postgresql&consumeDelete=false")
        .process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                Operation operation = exchange.getIn().getBody( Operation.class );
            
                Double amount = operation.getAmount();
              
                exchange.getIn().setHeader("amount", amount);
            }
        })   .log("Amount: ${headers.amount}");
       
                          
    }
}
