package com.advyteam.sygha.repository;

import com.advyteam.sygha.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role,String> {
    Role findByCode(String role);
}
