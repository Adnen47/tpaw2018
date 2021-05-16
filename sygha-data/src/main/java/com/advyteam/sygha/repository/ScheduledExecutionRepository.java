package com.advyteam.sygha.repository;

import com.advyteam.sygha.entity.ScheduledExecution;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScheduledExecutionRepository extends MongoRepository<ScheduledExecution, String> {
}
