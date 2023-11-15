package com.billy.operations.api.repository;

import com.billy.operations.api.model.Bill;
import com.billy.operations.api.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, UUID> {
    Person findByName(String name);
}
