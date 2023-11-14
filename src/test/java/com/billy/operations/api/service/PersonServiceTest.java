package com.billy.operations.api.service;

import com.billy.operations.api.model.Nationality;
import com.billy.operations.api.model.Person;
import com.billy.operations.api.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.billy.operations.api.controller.exception.NotFoundException;


import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PersonServiceTest {
    @InjectMocks
    PersonService personService;

    @Mock
    PersonRepository personRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addPerson() {
        Person personToAdd = new Person();
        personToAdd.setName("BOB");
        personToAdd.setLastName("SquarePants");
        personToAdd.setBirthYear(2002);
        personToAdd.setRFC("235423523523");
        personToAdd.setNationality(Nationality.valueOf("MX"));

        when(personRepository.save(any(Person.class))).thenReturn(personToAdd);


        Person addedPerson = personService.addPerson(personToAdd);

        assertNotNull(addedPerson);
        assertEquals("BOB", addedPerson.getName());
        verify(personRepository, times(1)).save(any(Person.class));
    }

    @Test
    void updatePerson() {
        UUID personId = UUID.randomUUID();
        Person updatedPersonData = new Person();
        updatedPersonData.setName("UpdatedName");
        updatedPersonData.setLastName("UpdatedLastName");
        updatedPersonData.setBirthYear(1990);
        updatedPersonData.setRFC("UpdatedRFC");
        updatedPersonData.setNationality(Nationality.valueOf("US"));

        when(personRepository.findById(eq(personId))).thenReturn(java.util.Optional.of(new Person())); // assuming a person with given ID exists
        when(personRepository.save(any(Person.class))).thenReturn(updatedPersonData);

        Person updatedPerson = personService.updatePerson(personId, updatedPersonData);

        assertNotNull(updatedPerson);
        assertEquals("UpdatedName", updatedPerson.getName());

        verify(personRepository, times(1)).findById(eq(personId));
        verify(personRepository, times(1)).save(any(Person.class));
    }
    @Test
    void updatePerson_ThrowsNotFoundException() {
        UUID personId = UUID.randomUUID();
        Person updatedPerson = new Person();
        updatedPerson.setName("UpdatedName");
        updatedPerson.setBirthYear(1990);

        when(personRepository.findById(eq(personId))).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> personService.updatePerson(personId, updatedPerson));

        verify(personRepository, times(1)).findById(eq(personId));

        String expectedErrorMessage = "Person not found with ID: " + personId;
        String actualErrorMessage = exception.getMessage();
        assertTrue(actualErrorMessage.contains(expectedErrorMessage), "Expected error message not found");
    }
}

