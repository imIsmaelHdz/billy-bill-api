package com.billy.operations.api.service;

import com.billy.operations.api.controller.exception.PersonNotFoundException;
import com.billy.operations.api.model.Bill;
import com.billy.operations.api.model.Person;
import com.billy.operations.api.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    @Transactional
    public Person addPerson(Person person) {return personRepository.save(person);}

    @Transactional
    public Person updatePerson(UUID id, Person updatedPerson) {
        return personRepository.findById(id)
                .map(personFound -> {
                    personFound.setName(updatedPerson.getName());
                    personFound.setEmail(updatedPerson.getEmail());
                    personFound.setLastName(updatedPerson.getLastName());
                    personFound.setBirthYear(updatedPerson.getBirthYear());
                    personFound.setRFC(updatedPerson.getRFC());
                    personFound.setNationality(updatedPerson.getNationality());
                    return personRepository.save(personFound);
                })
                .orElseThrow(() -> new PersonNotFoundException("Person not found with ID: " + id));
    }

    public Person findByName(String name) {
        Optional<Person> optionalPerson = Optional.ofNullable(personRepository.findByName(name));
        return optionalPerson.orElseThrow(() -> new PersonNotFoundException("Person not found with Name: " + name));
    }

    @Transactional
    public void addBillToPerson(Bill bill) {
        Person person = findByName(bill.getOwner());
        person.getBills().add(bill);
        personRepository.save(person);
    }
}
