package com.advyteam.sygha.DTO;

import com.advyteam.sygha.entity.ReferentielAudit;

public class CompanyDTO {

    private String code;
    private String label;
    private String siren;
    private ReferentielAudit referentielAudit;

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
}
