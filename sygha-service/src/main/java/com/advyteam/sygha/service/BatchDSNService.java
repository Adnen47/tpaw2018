package com.advyteam.sygha.service;

import com.advyteam.sygha.batch.JobDSNLaunch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchDSNService {
    @Autowired
    JobDSNLaunch jobDSNLaunch;

    public void launchJob() throws Exception {
        this.jobDSNLaunch.launchJob();
    }
}
