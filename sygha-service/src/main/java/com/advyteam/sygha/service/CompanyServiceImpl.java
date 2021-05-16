package com.advyteam.sygha.service;

import com.advyteam.sygha.DTO.CompanyDTO;
import com.advyteam.sygha.DTO.GroupDTO;
import com.advyteam.sygha.entity.Company;
import com.advyteam.sygha.entity.Group;
import com.advyteam.sygha.entity.ReferentielAudit;
import com.advyteam.sygha.entity.User;
import com.advyteam.sygha.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Repository
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    @Override
    public Company updateCompany(CompanyDTO companyDTO, User user){
        Company company = companyRepository.findByCode(companyDTO.getCode());
        company.setLabel(companyDTO.getLabel());
        company.setSiren(companyDTO.getSiren());

        Referentiel referentiel = new Referentiel();
        company.setReferentielAudit(referentiel.referentiel(user));

        return companyRepository.save(company);
    }

    @Override
    public Company addCompany(CompanyDTO companyDTO, User user) {
        Company company = new Company();

        company.setCode(companyDTO.getCode());
        company.setLabel(companyDTO.getLabel());
        company.setSiren(companyDTO.getSiren());

        Referentiel referentiel = new Referentiel();
        company.setReferentielAudit(referentiel.referentiel(user));

        return companyRepository.save(company);
    }

    @Override
    public Company deleteCompany(Company company){
        companyRepository.deleteById(company.getId());
        return company;
    }
}
