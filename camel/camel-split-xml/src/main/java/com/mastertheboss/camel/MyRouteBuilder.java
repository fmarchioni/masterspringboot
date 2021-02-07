package com.mastertheboss.camel;

import org.apache.camel.builder.RouteBuilder;


public class MyRouteBuilder extends RouteBuilder {


    public void configure() {


        from("file:src/data?noop=true")
                .split(xpath("//catalog/book/author/text()")).to("log:output");
    }

}
