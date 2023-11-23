package com.billy.operations.api.repository;

import com.billy.operations.api.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends Neo4jRepository<User, UUID> {
    User findByName(String name);
    User findByRFC(String rfc);
}
