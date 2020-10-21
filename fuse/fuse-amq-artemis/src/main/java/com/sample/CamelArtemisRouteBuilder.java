
package com.sample;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelArtemisRouteBuilder extends RouteBuilder {

    public void configure() throws Exception {


        from("timer:mytimer?period=5000").routeId("generate-route")
                .transform(constant("HELLO from Camel!"))
                .to("jms:queue:QueueIN");


        from("jms:queue:QueueIN").routeId("receive-route")
                .log("Received a message - ${body} - sending to outbound queue")
                .to("jms:queue:QueueOUT?exchangePattern=InOnly");


    }
}