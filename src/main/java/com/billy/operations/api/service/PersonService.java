package com.billy.operations.api.service;

import com.billy.operations.api.controller.exception.NotFoundException;
import com.billy.operations.api.model.Person;
import com.billy.operations.api.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public Person addPerson(Person person) {return personRepository.save(person);}

    public Person updatePerson(UUID id, Person updatedPerson) {
        Optional<Person> existingPersonOptional = personRepository.findById(id);
        if (existingPersonOptional.isPresent()) {
            Person existingPerson = existingPersonOptional.get();
            existingPerson.setName(updatedPerson.getName());
            existingPerson.setBirthYear(updatedPerson.getBirthYear());
            return personRepository.save(existingPerson);
        } else {
            throw new NotFoundException("Person not found with ID: " + id );
        }
    }
}