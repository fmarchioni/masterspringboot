package com.masterspringboot.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MyProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {

        String myString = exchange.getIn().getBody(String.class);
        myString += System.getProperty("line.separator");
        myString += "Processed with MyProcessor";
        System.out.println("Processed!");
        exchange.getIn().setBody(myString);
    }

    public MyProcessor() {
    }

}