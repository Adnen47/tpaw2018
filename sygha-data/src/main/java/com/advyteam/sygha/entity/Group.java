package com.advyteam.sygha.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@Document(collection = "GROUP")
public class Group {
    @Id
    private String id;
    @Field("code")
    private String code;
    @Field("label")
    private String label;
    @Field("address")
    private String address;
    @Field("companies")
    private List<Company> companies;

    @Field("audit")
    private ReferentielAudit referentielAudit;

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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


    public ReferentielAudit getReferentielAudit() {
        return referentielAudit;
    }

    public void setReferentielAudit(ReferentielAudit referentielAudit) {
        this.referentielAudit = referentielAudit;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", label='" + label + '\'' +
                ", companies='" + companies + '\'' +
                ", referentielAudit=" + referentielAudit +
                '}';
    }
}
