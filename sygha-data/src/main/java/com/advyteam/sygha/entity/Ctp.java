package com.advyteam.sygha.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document(collection = "CTP")
public class Ctp {

    @Id
    private String id;
    @Field("code")
    private String code;
    @Field("ctp_date")
    private LocalDate ctpDate;
    @Field("establishment")
    private Establishment establishment;

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

    public LocalDate getCtpDate() {
        return ctpDate;
    }

    public void setCtpDate(LocalDate ctpDate) {
        this.ctpDate = ctpDate;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    @Override
    public String toString() {
        return "Ctp{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", ctpDate=" + ctpDate +
                ", establishment=" + establishment +
                '}';
    }
}
