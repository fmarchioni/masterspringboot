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
public class MainApp2 {

    public static void main(String args[]) throws Exception {

        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("direct:transferFile")
                        .process(new MyProcessor())
                        .to("file:target/messages/others");
            }
        });

        ProducerTemplate producerTemplate = context.createProducerTemplate();

        context.start();
        String fileText = new String(Files.readAllBytes(Paths.get("src/data/file.txt")), StandardCharsets.UTF_8);

        Map<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put(Exchange.FILE_NAME, "file.txt");

        producerTemplate.sendBodyAndHeaders("direct:transferFile", fileText, headerMap);
        Thread.sleep(5000);
        context.stop();
    }

}

