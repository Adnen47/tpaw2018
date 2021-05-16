package com.advyteam.sygha.DTO;


import com.advyteam.sygha.entity.Company;

import java.math.BigDecimal;

public class EstablishmentDTO {

    private String code;
    private String label;
    private String siret;
    private String nic;
    private BigDecimal fraction;
    private BigDecimal rateAT;
    private Company company;

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

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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
}
