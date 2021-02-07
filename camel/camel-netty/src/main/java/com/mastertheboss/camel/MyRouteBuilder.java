package com.mastertheboss.camel;

import org.apache.camel.builder.RouteBuilder;


public class MyRouteBuilder extends RouteBuilder {

    public void configure() {

        from("netty:tcp://localhost:8001?textline=true&sync=false").to("log:?level=INFO&showBody=true");
    }

}
