package com.springbatchexample.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobListener implements JobExecutionListener {

    private static final Logger logger = LoggerFactory.getLogger(JobListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution){
        logger.info("JobListener: beforeJob {}", jobExecution.getStatus());
    }

    @Override
    public void afterJob(JobExecution jobExecution){
 
        logger.info("JobListener: afterJob  {}", jobExecution.getStatus());
    }
}
