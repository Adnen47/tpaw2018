package com.advyteam.sygha.batch.listener;

public class JobProgressMessage {
    private String status;
    private String fileName;
    private double writeCount;
    private double percentageComplete;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public double getWriteCount() {
        return writeCount;
    }

    public void setWriteCount(double writeCount) {
        this.writeCount = writeCount;
    }

    public void setPercentageComplete(double percentageComplete) {
        this.percentageComplete = percentageComplete;
    }

    public double getPercentageComplete() {
        return percentageComplete;
    }

    @Override
    public String toString() {
        return "JobProgressMessage{" +
                "status='" + status + '\'' +
                ", fileName='" + fileName + '\'' +
                ", writeCount=" + writeCount +
                ", percentageComplete=" + percentageComplete +
                '}';
    }
}
