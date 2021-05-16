package com.advyteam.sygha.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class JobDSNLaunch {
    @Autowired
    @Qualifier("importDSNeJob")
    Job importDSNeJob;
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    private JobBuilderFactory jobs;

    public void launchJob() throws Exception {
        jobLauncher.run(importDSNeJob,
                new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters());
    }
}
