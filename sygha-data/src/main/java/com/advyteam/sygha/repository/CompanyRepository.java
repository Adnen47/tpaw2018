package com.advyteam.sygha.repository;

import com.advyteam.sygha.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {

    Company findBySiren (String siren);

    Company insert(Company company);

    Company findByCode (String code);

    void deleteById(String id);


}
