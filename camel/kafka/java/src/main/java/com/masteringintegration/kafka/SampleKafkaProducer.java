package com.masteringintegration.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

// Example class sending text Messages to Kafka cluster

public class SampleKafkaProducer extends Thread {
    private final KafkaProducer<Integer, String> producer;
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
        properties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
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
            String messageStr = "This is Message number:" + messageNo;
            long startTime = System.currentTimeMillis();
            if (isAsync) { // Send asynchronously
                producer.send(new ProducerRecord<>(topic,
                        messageNo,
                        messageStr), new MessageCallBack(startTime, messageNo, messageStr));
            } else { // Send synchronously
                try {
                    producer.send(new ProducerRecord<>(topic,
                            messageNo,
                            messageStr)).get();
                    System.out.println("Sent message: (" + messageNo + ", " + messageStr + ")");
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
    private final int key;
    private final String message;

    public MessageCallBack(long startTime, int key, String message) {
        this.startTime = startTime;
        this.key = key;
        this.message = message;
    }


    public void onCompletion(RecordMetadata metadata, Exception exception) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (metadata != null) {
            System.out.println(
                    "message(" + key + ", " + message + ") sent to partition(" + metadata.partition() +
                            "), " +
                            "offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
        } else {
            exception.printStackTrace();
        }
    }
}