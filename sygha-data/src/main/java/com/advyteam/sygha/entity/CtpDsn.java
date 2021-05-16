package com.advyteam.sygha.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "CTP_DSN")
public class CtpDsn {

    @Id
    private String id;
    @Field("code")
    private String code;
    @Field("dsn_code")
    private String dsnCode;
    @Field("label")
    private String label;
    @Field("base_type")
    private String baseType;
    @Field("rate")
    private String rate;
    @Field("audit")
    private ReferentielAudit referentielAudit;


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

    public String getDsnCode() {
        return dsnCode;
    }

    public void setDsnCode(String dsnCode) {
        this.dsnCode = dsnCode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getBaseType() {
        return baseType;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public ReferentielAudit getReferentielAudit() {
        return referentielAudit;
    }

    public void setReferentielAudit(ReferentielAudit referentielAudit) {
        this.referentielAudit = referentielAudit;
    }

    @Override
    public String toString() {
        return "CtpDsn{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", dsnCode='" + dsnCode + '\'' +
                ", label='" + label + '\'' +
                ", baseType='" + baseType + '\'' +
                ", rate='" + rate + '\'' +
                ", referentielAudit=" + referentielAudit +
                '}';
    }
}
