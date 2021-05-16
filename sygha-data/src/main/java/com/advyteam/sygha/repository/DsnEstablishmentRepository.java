package com.advyteam.sygha.repository;

import com.advyteam.sygha.entity.DsnEstablishment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DsnEstablishmentRepository extends MongoRepository<DsnEstablishment, String> {
}
