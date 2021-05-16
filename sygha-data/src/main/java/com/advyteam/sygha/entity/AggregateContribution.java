package com.advyteam.sygha.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document(collection = "AGGREGATE_CONTRIBUTION")
public class AggregateContribution {

    @Id
    private String id;
    @Field("code")
    private String code;
    @Field("qualifiant")
    private String qualifiant;
    @Field("rate")
    private BigDecimal rate;
    @Field("amount")
    private BigDecimal amount;
    @Field("contribution_amount")
    private BigDecimal contributionAmount;
    @Field("insee_commune")
    private BigDecimal inseeCommune;
    @Field("contribution_note")
    private ContributionNote contributionNote;

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

    public String getQualifiant() {
        return qualifiant;
    }

    public void setQualifiant(String qualifiant) {
        this.qualifiant = qualifiant;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getContributionAmount() {
        return contributionAmount;
    }

    public void setContributionAmount(BigDecimal contributionAmount) {
        this.contributionAmount = contributionAmount;
    }

    public BigDecimal getInseeCommune() {
        return inseeCommune;
    }

    public void setInseeCommune(BigDecimal inseeCommune) {
        this.inseeCommune = inseeCommune;
    }

    public ContributionNote getContributionNote() {
        return contributionNote;
    }

    public void setContributionNote(ContributionNote contributionNote) {
        this.contributionNote = contributionNote;
    }

    @Override
    public String toString() {
        return "AggregateContribution{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", qualifiant='" + qualifiant + '\'' +
                ", rate=" + rate +
                ", amount=" + amount +
                ", contributionAmount=" + contributionAmount +
                ", inseeCommune=" + inseeCommune +
                ", contributionNote=" + contributionNote +
                '}';
    }
}
