package com.springbatchexample.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;

import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.springbatchexample.job.CustomItemWriter;

import com.springbatchexample.job.CustomProcessor;
import com.springbatchexample.job.CustomItemReader;


@Configuration
public class JobConfig {
    public static final String jobName = "JobExample";
    private static final Logger logger = LoggerFactory.getLogger(JobConfig.class);

    @Bean
    public Job createJob(JobRepository jobRepository,
                    JobListener listener,
                    Step step1){
        return new JobBuilder(jobName, jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public Step createStep(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager){
        return new StepBuilder("JobExample-step1", jobRepository)
                .<String, String>chunk(1,transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public CustomItemReader reader(){
        return new CustomItemReader();
    }

    @Bean
    public CustomProcessor processor(){
        return new CustomProcessor();
    }

    @Bean
    public CustomItemWriter writer(){
        return new CustomItemWriter();
    }

}
