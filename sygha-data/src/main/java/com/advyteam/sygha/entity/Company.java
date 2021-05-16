package com.advyteam.sygha.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "COMPANY")
public class Company {

    @Id
    private String id;
    @Field("code")
    private String code;
    @Field("label")
    private String label;
    @Field("siren")
    private String siren;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public ReferentielAudit getReferentielAudit() {
        return referentielAudit;
    }

    public void setReferentielAudit(ReferentielAudit referentielAudit) {
        this.referentielAudit = referentielAudit;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", label='" + label + '\'' +
                ", siren='" + siren + '\'' +
                ", referentielAudit=" + referentielAudit +
                '}';
    }
}
