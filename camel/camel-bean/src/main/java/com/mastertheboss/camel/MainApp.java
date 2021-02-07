package com.mastertheboss.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class MainApp {

    public static void main(String[] args) throws Exception {
        // create a CamelContext
        CamelContext camel = new DefaultCamelContext();


        camel.addRoutes(new MyRouteBuilder());

        // start is not blocking
        camel.start();

        // so run for 10 seconds
        Thread.sleep(10_000);

        // and then stop nicely
        camel.stop();
    }


}

