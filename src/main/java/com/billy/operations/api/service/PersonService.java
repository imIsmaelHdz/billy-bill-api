package com.billy.operations.api.service;

import com.billy.operations.api.controller.exception.PersonNotFoundException;
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
            existingPerson.setEmail(updatedPerson.getEmail());
            existingPerson.setLastName(updatedPerson.getLastName());
            existingPerson.setBirthYear(updatedPerson.getBirthYear());
            existingPerson.setRFC(updatedPerson.getRFC());
            existingPerson.setNationality(updatedPerson.getNationality());
            return personRepository.save(existingPerson);
        } else {
            throw new PersonNotFoundException("Person not found with ID: " + id );
        }
    }

    public Person findByName(String name) {
        return personRepository.findByName(name);

    }
}
