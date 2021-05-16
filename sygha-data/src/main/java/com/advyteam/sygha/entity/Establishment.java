package com.advyteam.sygha.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document(collection = "ESTABLISHMENT")
public class Establishment {

    @Id
    private String id;
    @Field("code")
    private String code;
    @Field("label")
    private String label;
    @Field("nic")
    private String nic;
    @Field("siret")
    private String siret;
    @Field("fraction")
    private BigDecimal fraction;
    @Field("rateAT")
    private BigDecimal rateAT;
    @Field("company")
    private  Company company;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public BigDecimal getFraction() {
        return fraction;
    }

    public void setFraction(BigDecimal fraction) {
        this.fraction = fraction;
    }

    public BigDecimal getRateAT() {
        return rateAT;
    }

    public void setRateAT(BigDecimal rateAT) {
        this.rateAT = rateAT;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ReferentielAudit getReferentielAudit() {
        return referentielAudit;
    }

    public void setReferentielAudit(ReferentielAudit referentielAudit) {
        this.referentielAudit = referentielAudit;
    }

    @Override
    public String toString() {
        return "Establishment{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", label='" + label + '\'' +
                ", nic='" + nic + '\'' +
                ", siret='" + siret + '\'' +
                ", fraction=" + fraction +
                ", rateAT=" + rateAT +
                ", company=" + company +
                ", referentielAudit=" + referentielAudit +
                '}';
    }
}
