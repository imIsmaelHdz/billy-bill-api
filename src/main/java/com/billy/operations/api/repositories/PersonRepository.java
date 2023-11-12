package com.billy.operations.api.repositories;

import com.billy.operations.api.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface PersonRepository extends Neo4jRepository<Person, UUID> {

    Person getPersonByName(String name);

}
