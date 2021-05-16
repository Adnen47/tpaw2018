package com.advyteam.sygha.service;

import com.advyteam.sygha.DTO.CompanyDTO;
import com.advyteam.sygha.DTO.EstablishmentDTO;
import com.advyteam.sygha.entity.*;
import com.advyteam.sygha.repository.EstablishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;
//
@Repository
public class EstablishmentServiceImpl implements EstablishmentService{

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Override
    public Response getAllEstablishment(int pageNo, int pageSize){
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Establishment> establishments = establishmentRepository.findAll(paging);
        return new Response(establishments.getContent(), establishments.getTotalPages(),
                establishments.getNumber(), establishments.getSize());
    }

    @Override
    public Establishment updateEstablishment(EstablishmentDTO establishmentDTO, User user){
        Establishment establishment = establishmentRepository.findByCode(establishmentDTO.getCode());
        establishment.setLabel(establishmentDTO.getLabel());
        establishment.setSiret(establishmentDTO.getSiret());
        establishment.setNic(establishmentDTO.getNic());
        establishment.setFraction(establishmentDTO.getFraction());
        establishment.setRateAT(establishmentDTO.getRateAT());
        establishment.setCompany(establishmentDTO.getCompany());

        Referentiel referentiel = new Referentiel();
        establishment.setReferentielAudit(referentiel.referentiel(user));


        return establishmentRepository.save(establishment);
    }

    @Override
    public Establishment addEstablishment(EstablishmentDTO establishmentDTO, User user) {
        Establishment establishment = new Establishment();

        establishment.setCode(establishmentDTO.getCode());
        establishment.setLabel(establishmentDTO.getLabel());
        establishment.setSiret(establishmentDTO.getSiret());
        establishment.setNic(establishmentDTO.getNic());
        establishment.setFraction(establishmentDTO.getFraction());
        establishment.setRateAT(establishmentDTO.getRateAT());
        establishment.setCompany(establishmentDTO.getCompany());

        Referentiel referentiel = new Referentiel();
        establishment.setReferentielAudit(referentiel.referentiel(user));

        return establishmentRepository.save(establishment);
    }

    @Override
    public Establishment deleteEstablishment(Establishment establishment){
        establishmentRepository.deleteById(establishment.getId());
        return establishment;
    }
}
