package com.sample;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.main.Main;

 

/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
       
        Main main = new Main();

        main.configure().addRoutesBuilder(new MyRouteBuilder());
        main.start();
        CamelContext context = main.getCamelContext();
        
        ProducerTemplate producerTemplate = context.createProducerTemplate();

        Person person = new Person("Alice", 30);

        // Sending the Person object to the route
        producerTemplate.sendBody("direct:beanToJson", person);

        Thread.sleep(2000); // Sleep to allow time for the route to process

     
        main.run(args);
    }
   
}

