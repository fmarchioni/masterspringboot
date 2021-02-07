package com.masteringintegration.kafka;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.errors.SerializationException;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StockQuoteSerializer implements Serializera {
    private ObjectMapper objectMapper;
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        this.objectMapper = new ObjectMapper();
    }
    @Override
    public byte[] serialize(String topic, StockQuote quote) {
        try {
            return objectMapper.writeValueAsBytes(quote);
        } catch (Exception e) {
            throw new SerializationException("Error serializing object", e);
        }
    }
    @Override
    public void close() {
    }
}