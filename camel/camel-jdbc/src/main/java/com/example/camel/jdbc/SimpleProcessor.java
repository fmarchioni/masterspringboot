package com.example.camel.jdbc;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SimpleProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {

        exchange.getIn().setBody("select now()");

    }
}