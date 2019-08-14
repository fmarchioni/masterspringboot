package com.masterspringboot.quartz.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

public class JobTwo implements Job {
    @Override
    public void execute(JobExecutionContext context) {

        JobDataMap jobDataMap = context.getMergedJobDataMap();
        String data = jobDataMap.getString("somedata");
        System.out.println("Job Two fired with data: "+data);
    }
}
