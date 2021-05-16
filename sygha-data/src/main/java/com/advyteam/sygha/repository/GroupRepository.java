package com.advyteam.sygha.repository;

import com.advyteam.sygha.entity.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupRepository extends MongoRepository<Group, String> {

    Group findByCode (String code);

    Group insert(Group group);



}
