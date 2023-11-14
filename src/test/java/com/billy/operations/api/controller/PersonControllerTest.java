package com.billy.operations.api.controller;

import com.billy.operations.api.model.Nationality;
import com.billy.operations.api.model.Person;
import com.billy.operations.api.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PersonRepository personRepository;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        this.baseUrl = "http://localhost:" + port + "/people";
    }

    @Test
    void testAddPerson() {
        Person personToAdd = new Person();
        personToAdd.setName("John Doe");
        personToAdd.setBirthYear(1990);
        personToAdd.setRFC("ABC123");
        personToAdd.setNationality(Nationality.valueOf("US"));

        ResponseEntity<Person> responseEntity =
            restTemplate.postForEntity(baseUrl + "/create", personToAdd, Person.class);

        assertEquals(201, responseEntity.getStatusCodeValue());

        Person addedPerson = responseEntity.getBody();
        assertNotNull(addedPerson);
        assertNotNull(addedPerson.getCustomId());
        assertEquals(personToAdd.getName(), addedPerson.getName());
    }

    @Test
    void testUpdatePersonNotFound() {
        UUID nonExistentPersonId = UUID.randomUUID();
        Person updatedPersonData = new Person();
        updatedPersonData.setName("UpdatedName");
        updatedPersonData.setBirthYear(1990);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Person> requestEntity = new HttpEntity<>(updatedPersonData, headers);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Person> responseEntity = restTemplate.exchange(
                    baseUrl + "/" + nonExistentPersonId,
                    HttpMethod.PUT,
                    requestEntity,
                    Person.class);
        } catch (HttpClientErrorException e) {
            assertEquals(404, e.getRawStatusCode());

            assertNotNull(e.getResponseBodyAsString());
        }
    }

}
