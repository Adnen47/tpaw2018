package com.advyteam.sygha.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "REFERENTIEL_AUDIT")
public class ReferentielAudit {

    @Id
    private String id;

    @Field("code")
    private String code;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Field("update_date")
    private Date updateDate;

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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ReferentielAudit{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", updateDate=" + updateDate +
                ", user=" + user +
                '}';
    }
}
