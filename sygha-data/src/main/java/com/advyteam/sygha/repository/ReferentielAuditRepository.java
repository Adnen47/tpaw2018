package com.advyteam.sygha.repository;

import com.advyteam.sygha.entity.ReferentielAudit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReferentielAuditRepository extends MongoRepository<ReferentielAudit, String> {
}
