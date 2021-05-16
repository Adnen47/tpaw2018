package com.advyteam.sygha.repository;

import com.advyteam.sygha.entity.DsnRecordedEstablishment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hboulahia on 07/11/19.
 */
@Repository
public interface DsnRecordedEstablishmentRepository extends MongoRepository<DsnRecordedEstablishment, String> {

    DsnRecordedEstablishment findById (ObjectId dsnRecordedEstablishment);

    DsnRecordedEstablishment insert(DsnRecordedEstablishment dsnRecordedEstablishment);

    DsnRecordedEstablishment save(DsnRecordedEstablishment dsnRecordedEstablishment);
}
