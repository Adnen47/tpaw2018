package com.advyteam.sygha.service;

import com.advyteam.sygha.DTO.CompanyDTO;
import com.advyteam.sygha.entity.Company;
import com.advyteam.sygha.entity.User;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    Company updateCompany(CompanyDTO company, User user);

    Company addCompany(CompanyDTO company, User user);

    Company deleteCompany(Company company);
}
