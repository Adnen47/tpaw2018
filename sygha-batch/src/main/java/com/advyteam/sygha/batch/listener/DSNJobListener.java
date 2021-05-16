package com.advyteam.sygha.batch.listener;

import com.advyteam.sygha.entity.DSNIBloctem;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class DSNJobListener implements ItemWriteListener<DSNIBloctem>, JobExecutionListener{

    private final static Logger LOGGER = Logger.getLogger(DSNJobListener.class.getName());

    @Value("${dsn.input.path}")
    private String dsnInputPath;

    private SimpMessagingTemplate simpMessagingTemplate;
    private String fileName;
    private AtomicInteger runningWriteCount = new AtomicInteger(0);
    private JobExecution jobExecution;
    private int recordCount;

    @Override
    public void beforeWrite(List<? extends DSNIBloctem> list) {

    }

    @Override
    public void afterWrite(List<? extends DSNIBloctem> list) {
        double runningWriteCount = this.runningWriteCount.addAndGet(list.size());
        double percentageComplete = (runningWriteCount / recordCount) * 100;
        JobProgressMessage jobProgressMessage = new JobProgressMessage();
        jobProgressMessage.setStatus("RUNNING");
        jobProgressMessage.setWriteCount(runningWriteCount);
        jobProgressMessage.setPercentageComplete(percentageComplete);
        jobProgressMessage.setFileName(fileName);
        LOGGER.info(jobProgressMessage.toString());
        simpMessagingTemplate.convertAndSend("/topic/public", jobProgressMessage);
    }

    @Override
    public void onWriteError(Exception e, List<? extends DSNIBloctem> list) {

    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        this.jobExecution = jobExecution;
        try {
            recordCount = countLines(dsnInputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public SimpMessagingTemplate getSimpMessagingTemplate() {
        return simpMessagingTemplate;
    }

    public void setSimpMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public static int countLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];

            int readChars = is.read(c);
            if (readChars == -1) {
                // bail out if nothing to read
                return 0;
            }

            // make it easy for the optimizer to tune this loop
            int count = 0;
            while (readChars == 1024) {
                for (int i=0; i<1024;) {
                    if (c[i++] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            // count remaining characters
            while (readChars != -1) {
                System.out.println(readChars);
                for (int i=0; i<readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            return count == 0 ? 1 : count;
        } finally {
            is.close();
        }
    }
}
