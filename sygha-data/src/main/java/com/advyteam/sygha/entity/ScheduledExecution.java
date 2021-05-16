package com.advyteam.sygha.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "SCHEDULED_EXECUTION")
public class ScheduledExecution {

    @Id
    private String id;
    @Field("code")
    private String code;
    @Field("dsn_file")
    private String dsnFile;
    @Field("perpai")
    private LocalDate perpai;
    @Field("execution_time")
    private LocalDateTime executionTime;
    @Field("status")
    private String status;
    @Field("user")
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDsnFile() {
        return dsnFile;
    }

    public void setDsnFile(String dsnFile) {
        this.dsnFile = dsnFile;
    }

    public LocalDate getPerpai() {
        return perpai;
    }

    public void setPerpai(LocalDate perpai) {
        this.perpai = perpai;
    }

    public LocalDateTime getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(LocalDateTime executionTime) {
        this.executionTime = executionTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ScheduledExecution{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", dsnFile='" + dsnFile + '\'' +
                ", perpai=" + perpai +
                ", executionTime=" + executionTime +
                ", status='" + status + '\'' +
                ", user=" + user +
                '}';
    }
}
