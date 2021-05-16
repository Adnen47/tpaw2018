package com.advyteam.sygha.repository;

import com.advyteam.sygha.entity.AggregateContribution;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AggregateContributionRepository extends MongoRepository<AggregateContribution, String> {
}
