package com.advyteam.sygha.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "CONTRIBUTION_NOTE")
public class ContributionNote {

    @Id
    private String id;
    @Field("code")
    private String code;
    @Field("social_protection_code")
    private String socialProtectionCode;
    @Field("start_date")
    private LocalDate startDate;
    @Field("end_date")
    private LocalDate endDate;
    @Field("total_contribution_amount")
    private BigDecimal totalContributionAmount;
    @Field("establishment")
    private DsnEstablishment dsnEstablishment;

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

    public String getSocialProtectionCode() {
        return socialProtectionCode;
    }

    public void setSocialProtectionCode(String socialProtectionCode) {
        this.socialProtectionCode = socialProtectionCode;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getTotalContributionAmount() {
        return totalContributionAmount;
    }

    public void setTotalContributionAmount(BigDecimal totalContributionAmount) {
        this.totalContributionAmount = totalContributionAmount;
    }

    public DsnEstablishment getDsnEstablishment() {
        return dsnEstablishment;
    }

    public void setDsnEstablishment(DsnEstablishment dsnEstablishment) {
        this.dsnEstablishment = dsnEstablishment;
    }

    @Override
    public String toString() {
        return "ContributionNote{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", socialProtectionCode='" + socialProtectionCode + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalContributionAmount=" + totalContributionAmount +
                ", dsnEstablishment=" + dsnEstablishment +
                '}';
    }
}
