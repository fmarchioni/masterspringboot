package com.springbatchexample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 

@RestController
@RequestMapping("/batch")
public class BatchJobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;  

    private final static Logger logger = LoggerFactory.getLogger(BatchJobController.class);

    @GetMapping("/startjob")
    public BatchStatus startJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        JobExecution jobExecution = jobLauncher.run(job, jobParameters);

        logger.info("Job " + job.getName() + " done...");
        

        return jobExecution.getStatus();
    }
}