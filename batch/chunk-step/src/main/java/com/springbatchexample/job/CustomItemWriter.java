package com.springbatchexample.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;



public class CustomItemWriter implements ItemWriter<String> {

    private final static Logger logger = LoggerFactory.getLogger(CustomItemReader.class);

    @Override
    public void write(Chunk<? extends String> chunk) throws Exception {
        logger.info("Custom Item Writer {}", chunk.getItems().get(0));
        Thread.sleep(1000 * 1);
    }
}
