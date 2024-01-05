package com.sample;

 
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
 

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
        from("direct:beanToJson")
                .marshal().json(JsonLibrary.Jackson)
                .log("${prettyBody}");

    }

}
