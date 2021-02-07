package com.mastertheboss.camel;

import org.apache.camel.builder.RouteBuilder;


public class MyRouteBuilder3 extends RouteBuilder {


    public void configure() {


        from("file:src/data?noop=true")
                .split(xpath("//catalog/book/author[@age<40]/text()")).to("log:output");
    }

}
