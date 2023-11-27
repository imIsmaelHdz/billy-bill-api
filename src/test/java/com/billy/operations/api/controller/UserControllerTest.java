package com.billy.operations.api.controller;

import com.billy.operations.api.model.JobNationality;
import com.billy.operations.api.model.Profile;
import com.billy.operations.api.model.User;
import com.billy.operations.api.repository.UserRepository;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        this.baseUrl = "http://localhost:" + port + "/users";
    }

    @Test
    void testAddUserWithBasicData() {
        User userToAdd = new User();
        userToAdd.setCustomId(UUID.randomUUID());
        userToAdd.setProfile(Profile.ADMINISTRATOR);
        userToAdd.setName("John Doe");
        userToAdd.setBirthYear(1990);
        userToAdd.setRFC("ABC123");
        userToAdd.setPhoneNumber("3332320032");
        userToAdd.setJobNationality(JobNationality.MX);

        ResponseEntity<User> responseEntity =
            restTemplate.postForEntity(baseUrl + "/create", userToAdd, User.class);

        assertEquals(201, responseEntity.getStatusCodeValue());

        User addedUser = responseEntity.getBody();
        assertNotNull(addedUser);
        assertNotNull(addedUser.getCustomId());
        assertEquals(userToAdd.getName(), addedUser.getName());
    }

    @Test
    void testUpdateUserNotFound() {
        UUID nonExistentUserId = UUID.randomUUID();
        User updatedUserData = new User();
        updatedUserData.setName("UpdatedName");
        updatedUserData.setBirthYear(1990);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<User> requestEntity = new HttpEntity<>(updatedUserData, headers);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<User> responseEntity = restTemplate.exchange(
                    baseUrl + "/" + nonExistentUserId,
                    HttpMethod.PUT,
                    requestEntity,
                    User.class);
        } catch (HttpClientErrorException e) {
            assertEquals(404, e.getRawStatusCode());

            assertNotNull(e.getResponseBodyAsString());
        }
    }

}
