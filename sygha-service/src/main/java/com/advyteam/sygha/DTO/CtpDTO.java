package com.advyteam.sygha.DTO;


import com.advyteam.sygha.entity.ReferentielAudit;

public class CtpDTO {

    private String code;
    private String dsnCode;
    private String label;
    private String baseType;
    private String rate;
    private ReferentielAudit referentielAudit;

    public ReferentielAudit getReferentielAudit() {
        return referentielAudit;
    }

    public void setReferentielAudit(ReferentielAudit referentielAudit) {
        this.referentielAudit = referentielAudit;
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
}
