package com.masteringintegration.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

// Example class sending text Messages to Kafka cluster

public class SampleKafkaProducer extends Thread {
    private final KafkaProducer<String, StockQuote> producer;
    private final String topic;
    private final Boolean isAsync;

    public static final String KAFKA_SERVER_URL = "localhost";
    public static final int KAFKA_SERVER_PORT = 9092;
    public static final String CLIENT_ID = "SampleProducer";
    public static final String TOPIC = "testTopic";
    public static final int MESSAGES = 100;

    public SampleKafkaProducer(String topic, Boolean isAsync) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", KAFKA_SERVER_URL + ":" + KAFKA_SERVER_PORT);
        properties.put("client.id", CLIENT_ID);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.masteringintegration.kafka.StockQuoteSerializer");
        producer = new KafkaProducer(properties);
        this.topic = topic;
        this.isAsync = isAsync;
    }

    public static void main( String[] args )
    {

        boolean isAsync = false;
        SampleKafkaProducer producer = new SampleKafkaProducer(TOPIC, isAsync);
        // start the producer
        producer.start();
    }

    public void run() {
        int messageNo = 1;
        while (messageNo < MESSAGES) {
            String key = String.valueOf(messageNo);
            StockQuote quote = new StockQuote();
            long startTime = System.currentTimeMillis();
            if (isAsync) { // Send asynchronously
                producer.send(new ProducerRecord<>(topic,
                        key,
                        quote), new MessageCallBack(startTime, key, quote));
            } else { // Send synchronously
                try {
                    producer.send(new ProducerRecord<>(topic,
                            key,
                            quote)).get();
                    System.out.println("Sent Quote: (" + key + ", " + quote + ")");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    // handle the exception
                }
            }
            ++messageNo;
        }
    }
}

class MessageCallBack implements Callback {

    private final long startTime;
    private final String key;
    private final StockQuote quote;

    public MessageCallBack(long startTime, String key, StockQuote quote) {
        this.startTime = startTime;
        this.key = key;
        this.quote = quote;
    }


    public void onCompletion(RecordMetadata metadata, Exception exception) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (metadata != null) {
            System.out.println(
                    "quote(" + key + ", " + quote + ") sent to partition(" + metadata.partition() +
                            "), " +
                            "offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
        } else {
            exception.printStackTrace();
        }
    }
}