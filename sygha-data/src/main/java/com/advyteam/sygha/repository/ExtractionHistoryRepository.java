package com.advyteam.sygha.repository;

import com.advyteam.sygha.entity.ExtractionHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExtractionHistoryRepository extends MongoRepository<ExtractionHistory, String> {
}
