package com.advyteam.sygha.repository;

import com.advyteam.sygha.entity.ContributionNote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContributionNoteRepository extends MongoRepository<ContributionNote, String> {
}
