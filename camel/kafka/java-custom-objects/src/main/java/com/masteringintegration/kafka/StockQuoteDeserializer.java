package com.masteringintegration.kafka;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StockQuoteDeserializer implements Deserializer<StockQuote> {
    @Override public void close() {
    }
    @Override public void configure(Map<String, ?> arg0, boolean arg1) {
    }
    @Override
    public StockQuote deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        StockQuote quote = null;
        try {
            quote = mapper.readValue(arg1, StockQuote.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quote;
    }
}