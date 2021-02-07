package com.example.camel;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;


public class MainApp {
    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://0.0.0.0:61616");
        context.addComponent("jms",
                JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("timer:foo?period=1s").setBody(simple("Message at ${date:now:yyyy-MM-dd HH:mm:ss}")).to(
                        "jms:queue:activemq/queue/TestQueue");
            }
        });
        context.start();
        Thread.sleep(10000);
        context.stop();
    }
}