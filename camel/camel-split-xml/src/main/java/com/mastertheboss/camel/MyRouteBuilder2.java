package com.mastertheboss.camel;

import org.apache.camel.builder.RouteBuilder;


public class MyRouteBuilder2 extends RouteBuilder {


    public void configure() {


        from("file:src/data?noop=true")
                .split(xpath("//catalog/book[@id='id100']/author/text()")).to("log:output");
    }

}
