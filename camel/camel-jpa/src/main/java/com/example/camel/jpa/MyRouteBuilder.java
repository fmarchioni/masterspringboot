package com.example.camel.jpa;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JaxbDataFormat;


public class MyRouteBuilder extends RouteBuilder {

    public void configure() {
        JaxbDataFormat jxb = new JaxbDataFormat();
        jxb.setContextPath("com.example.camel.jpa.model");

        from("file:src/data?noop=true")
                .unmarshal(jxb)
                .to("jpa:com.example.camel.jpa.model.Person");

    }
}