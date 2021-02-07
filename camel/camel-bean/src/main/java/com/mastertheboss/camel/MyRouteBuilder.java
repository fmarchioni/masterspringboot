package com.mastertheboss.camel;

import org.apache.camel.builder.RouteBuilder;



public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer://myTimer?period=2000")
                .setBody()
                .simple("Hello World Camel fired at:")
                .bean(new MyBean(), "setTime(${body})")
                .bean(new MyBean(), "done()");




    }

}
