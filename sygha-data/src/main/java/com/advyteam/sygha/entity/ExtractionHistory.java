package com.advyteam.sygha.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "EXTRACTION_HISTORY")
public class ExtractionHistory {

    @Id
    private String id;
    @Field("code")
    private String code;
    @Field("dsn_file")
    private String dsnFile;
    @Field("perpai")
    private LocalDate perpai;
    @Field("extraction_date")
    private LocalDateTime extractionDate;
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

    public LocalDateTime getExtractionDate() {
        return extractionDate;
    }

    public void setExtractionDate(LocalDateTime extractionDate) {
        this.extractionDate = extractionDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ExtractionHistory{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", dsnFile='" + dsnFile + '\'' +
                ", perpai=" + perpai +
                ", extractionDate=" + extractionDate +
                ", user=" + user +
                '}';
    }
}
