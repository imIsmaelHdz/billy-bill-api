package com.billy.operations.api.repository;

import com.billy.operations.api.model.Bill;
import com.billy.operations.api.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BillRepository extends Neo4jRepository<Bill, UUID> {
    Iterable<Bill> findByOwner(Person owner);
}
