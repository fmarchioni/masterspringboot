package com.mastertheboss.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;

public class MainApp {

    public static void main(String... args) throws Exception {
        // use Camels Main class
        CamelContext ctx = new DefaultCamelContext();

        ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
        cf.setBrokerURL("tcp://localhost:61616");
        cf.setUser("admin");
        cf.setPassword("admin");

        ctx.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(cf));
        ctx.addRoutes(
                new RouteBuilder() {
                    public void configure() {
                        from("timer:mytimer?period=5000").routeId("generate-route")
                                .transform(constant("HELLO from Camel!"))
                                .to("jms:queue:QueueIN");


                        from("jms:queue:QueueIN").routeId("receive-route")
                                .log("Received a message - ${body} - sending to outbound queue")
                                .to("jms:queue:QueueOUT?exchangePattern=InOnly");
                    }
                });
        ctx.start();
        Thread.sleep(10000);
        ctx.stop();


    }

}

