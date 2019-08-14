package com.masterspringboot.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class JobOne implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        System.out.println("Hello from Job One!");
    }
}
