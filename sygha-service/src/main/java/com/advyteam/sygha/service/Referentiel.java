package com.advyteam.sygha.service;

import com.advyteam.sygha.entity.ReferentielAudit;
import com.advyteam.sygha.entity.User;

import java.util.Date;

public class Referentiel {

    public ReferentielAudit referentiel(User user){
        ReferentielAudit referentiel = new ReferentielAudit();
        referentiel.setCode("AAAA");
        referentiel.setUser(user);
        referentiel.setUpdateDate(new Date());
        return referentiel;
    }
}
