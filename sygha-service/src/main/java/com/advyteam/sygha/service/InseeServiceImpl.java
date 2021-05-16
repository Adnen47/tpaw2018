package com.advyteam.sygha.service;

import com.advyteam.sygha.DTO.GroupDTO;
import com.advyteam.sygha.DTO.InseeDTO;
import com.advyteam.sygha.entity.Group;
import com.advyteam.sygha.entity.Insee;
import com.advyteam.sygha.entity.ReferentielAudit;
import com.advyteam.sygha.entity.User;
import com.advyteam.sygha.repository.InseeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InseeServiceImpl implements InseeService{

    @Autowired
    InseeRepository inseeRepository;

    public List<Insee> getAllInsee() {
        return inseeRepository.findAll();
    }

    @Override
    public Insee updateInsee(InseeDTO inseeDTO, User user) {
        Insee insee = inseeRepository.findByCode(inseeDTO.getCode());
        insee.setTransportRate(inseeDTO.getTransportRate());

        Referentiel referentiel = new Referentiel();
        insee.setReferentielAudit(referentiel.referentiel(user));

        return inseeRepository.save(insee);
    }

    @Override
    public Insee addInsee(InseeDTO inseeDTO, User user) {
        Insee insee = new Insee();

        insee.setCode(inseeDTO.getCode());
        insee.setTransportRate(inseeDTO.getTransportRate());

        Referentiel referentiel = new Referentiel();
        insee.setReferentielAudit(referentiel.referentiel(user));

        return inseeRepository.save(insee);
    }

    @Override
    public Insee deleteInsee(Insee insee){
        inseeRepository.deleteById(insee.getId());
        return insee;
    }
}
