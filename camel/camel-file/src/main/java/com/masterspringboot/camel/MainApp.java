package com.masterspringboot.camel;

import org.apache.camel.main.Main;
import org.apache.camel.ProducerTemplate;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.Exchange;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class MainApp {

    public static void main(String args[]) throws Exception {

        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("file:src/data?noop=true")
                        .process(new MyProcessor())
                        .to("file:target/messages/others");
            }
        });



        context.start();
        Thread.sleep(5000);
        context.stop();
    }

}

