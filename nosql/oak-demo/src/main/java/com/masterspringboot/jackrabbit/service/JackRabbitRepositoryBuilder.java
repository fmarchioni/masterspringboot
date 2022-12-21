package com.masterspringboot.jackrabbit.service;

import org.apache.jackrabbit.oak.Oak;
import org.apache.jackrabbit.oak.jcr.Jcr;
import org.apache.jackrabbit.oak.plugins.document.mongo.MongoDocumentNodeStoreBuilder;
import org.apache.jackrabbit.oak.plugins.document.DocumentNodeStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.jcr.Repository;

@Configuration
public class JackRabbitRepositoryBuilder {
    static Repository repo = null;
    static Logger logger = LoggerFactory.getLogger(JackRabbitRepositoryBuilder.class);


    public static Repository getRepo(String host, final int port) {
        try {
            String uri = "mongodb://" + host + ":" + port;
            DocumentNodeStore ns = new MongoDocumentNodeStoreBuilder()
    .setMongoDB("mongodb://localhost:27017", "oak", 0).build();
            repo = new Jcr(new Oak(ns)).createRepository();
        } catch (Exception e) {
            logger.error("Exception caught: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

        return repo;
    }
}
