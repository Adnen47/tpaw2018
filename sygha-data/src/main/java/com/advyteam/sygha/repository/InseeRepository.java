package com.advyteam.sygha.repository;

import com.advyteam.sygha.entity.Insee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InseeRepository extends MongoRepository<Insee, String> {
    Insee findByCode(String code);
}
