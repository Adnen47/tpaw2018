package com.advyteam.sygha.repository;

import com.advyteam.sygha.entity.Establishment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
//
@Repository
public interface EstablishmentRepository extends PagingAndSortingRepository<Establishment,String> {

    Establishment findByCode(String code);

    Establishment findBySiret(String siret);

    Establishment findBySiretAndFraction(String siret, BigDecimal fraction);
}
