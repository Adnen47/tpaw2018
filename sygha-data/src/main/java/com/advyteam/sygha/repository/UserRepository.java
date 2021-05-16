package com.advyteam.sygha.repository;

import com.advyteam.sygha.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

    User findByEmail(String email);
}
