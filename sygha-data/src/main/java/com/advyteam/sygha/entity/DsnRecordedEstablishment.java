package com.advyteam.sygha.entity;

import com.mongodb.DBRef;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/**
 * Created by hboulahia on 04/11/19.
 */
@Document(collection = "DSN_RECORDED_ESTABLISHMENT")
public class DsnRecordedEstablishment {
    @Id
    private String id;
    @Field("dsnFile")
    private DBRef dsnFile;
    private DBRef establishment;
    private DBRef company;
    @Field("values")
    private List<DSNIBloctem> values;
    @Field("date")
    private Date date;

    public String getId() {return id;}


    public DBRef getDsnFile() {return dsnFile;}

    public DBRef getEstablishment() {return establishment;}

    public DBRef getCompany() {return company; }

    public List<DSNIBloctem> getValues() {return values;}

    public Date getDate() {return date;}

    public void setId(String id) {this.id = id;}

    public void setDsnFile(DBRef dsnFile) { this.dsnFile = dsnFile;}

    public void setEstablishment(DBRef establishment) {this.establishment = establishment;}

    public void setCompany(DBRef company) {this.company = company; }

    public void setValues(List<DSNIBloctem> values) { this.values = values;}

    public void setDate(Date date) {this.date = date;}

    @Override
    public String toString() {
        return "DsnRecordedEstablishment{" +
                "id='" + id + '\'' +
                ", dsnFile='" + dsnFile + '\'' +
                ", establishment=" + establishment +
                ", values=" + values +
                ", date=" + date +
                '}';
    }
}