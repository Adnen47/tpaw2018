package com.advyteam.sygha.repository;

import com.advyteam.sygha.entity.Ctp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CtpRepository extends MongoRepository<Ctp, String> {
}
