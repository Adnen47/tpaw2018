package com.advyteam.sygha.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "INSEE")
public class Insee {

    @Id
    private String id;
    @Field("code")
    private String code;
    @Field("transport_rate")
    private String transportRate;
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

    public String getTransportRate() {
        return transportRate;
    }

    public void setTransportRate(String transportRate) {
        this.transportRate = transportRate;
    }

    public ReferentielAudit getReferentielAudit() {
        return referentielAudit;
    }

    public void setReferentielAudit(ReferentielAudit referentielAudit) {
        this.referentielAudit = referentielAudit;
    }

    @Override
    public String toString() {
        return "Insee{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", transportRate='" + transportRate + '\'' +
                '}';
    }
}
