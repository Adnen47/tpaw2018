package com.advyteam.sygha.repository;

import com.advyteam.sygha.entity.CtpDsn;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CtpDsnRepository extends MongoRepository<CtpDsn, String> {
    CtpDsn findByCode(String code);
}
