package com.masterspringboot.quartz.job;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;


@Configuration
public class ConfigureQuartzJob {

    @Bean
    public JobDetail jobADetails() {
        return JobBuilder.newJob(JobOne.class).withIdentity("sampleJobA")
                .storeDurably().build();
    }

    @Bean
    public Trigger jobATrigger(JobDetail jobADetails) {

        return TriggerBuilder.newTrigger().forJob(jobADetails)

                .withIdentity("TriggerA")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * ? * * *"))
                .build();
    }


    @Bean
    public JobDetail jobBDetails() {
        return JobBuilder.newJob(JobTwo.class).withIdentity("sampleJobB")
                .storeDurably().build();
    }

    @Bean
    public Trigger jobBTrigger(JobDetail jobBDetails) {

        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("somedata", UUID.randomUUID().toString());

        return TriggerBuilder.newTrigger().forJob(jobBDetails)

                .withIdentity("TriggerB")

                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * ? * * *"))
                .usingJobData(jobDataMap)
                .build();
    }


}
