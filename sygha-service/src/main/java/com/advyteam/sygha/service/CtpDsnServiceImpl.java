package com.advyteam.sygha.service;

import com.advyteam.sygha.DTO.CtpDTO;
import com.advyteam.sygha.DTO.InseeDTO;
import com.advyteam.sygha.entity.*;
import com.advyteam.sygha.repository.CtpDsnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CtpDsnServiceImpl implements CtpDsnService{
    @Autowired
    CtpDsnRepository ctpDsnRepository;

    public List<CtpDsn> getAllCtp(){
        List<CtpDsn> ctpList = ctpDsnRepository.findAll();
        return ctpList;
    }

    @Override
    public CtpDsn updateCtp(CtpDTO ctpDTO, User user) {
        CtpDsn ctpDsn = ctpDsnRepository.findByCode(ctpDTO.getCode());
        ctpDsn.setLabel(ctpDTO.getLabel());
        ctpDsn.setRate(ctpDTO.getRate());


        //String id = UUID.randomUUID().toString();
       // referentielAudit.setId(id);
        Referentiel referentiel = new Referentiel();
        ctpDsn.setReferentielAudit(referentiel.referentiel(user));


        return ctpDsnRepository.save(ctpDsn);
    }

    @Override
    public CtpDsn addCtp(CtpDTO ctpDTO, User user) {
        CtpDsn ctpDsn = new CtpDsn();

        ctpDsn.setCode(ctpDTO.getCode());
        ctpDsn.setLabel(ctpDTO.getLabel());
        ctpDsn.setRate(ctpDTO.getRate());

        //String id = UUID.randomUUID().toString();
        //referentielAudit.setId(id);
        Referentiel referentiel = new Referentiel();
        ctpDsn.setReferentielAudit(referentiel.referentiel(user));

        return ctpDsnRepository.save(ctpDsn);
    }

    @Override
    public CtpDsn deleteCtp(CtpDsn ctpDsn){
        ctpDsnRepository.deleteById(ctpDsn.getId());
        return ctpDsn;
    }
}
