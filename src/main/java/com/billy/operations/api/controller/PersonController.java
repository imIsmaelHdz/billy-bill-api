package com.billy.operations.api.controller;

import com.billy.operations.api.model.Person;
import com.billy.operations.api.service.PersonService;
import com.billy.operations.api.repository.PersonRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/people")
public class PersonController {
    private final PersonRepository personRepository;
    private final PersonService personService;

    public PersonController(PersonRepository personRepository, PersonService personService) {
        this.personRepository = personRepository;
        this.personService = personService;
    }

    @Operation(summary = "Create a new person")
    @PostMapping("/create")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person savedPerson = personService.addPerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable UUID id, @RequestBody Person updatedPerson) {
        Person updated = personService.updatePerson(id, updatedPerson);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping
    public Iterable<Person> findAllPersons() {
        return personRepository.findAll();
    }

    @GetMapping("/{name}")
    public Person getPersonByName(@PathVariable String name) {
        return personRepository.findByName(name);
    }

}
