package com.billy.operations.api.service;

import com.billy.operations.api.model.Bill;
import com.billy.operations.api.model.JobNationality;
import com.billy.operations.api.model.User;
import com.billy.operations.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.billy.operations.api.controller.exception.UserNotFoundException;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addPerson() {
        User userToAdd = new User();
        userToAdd.setName("BOB");
        userToAdd.setLastName("SquarePants");
        userToAdd.setBirthYear(2002);
        userToAdd.setRFC("235423523523");
        userToAdd.setPhoneNumber("3332320032");
        userToAdd.setJobNationality(JobNationality.valueOf("MX"));

        when(userRepository.save(any(User.class))).thenReturn(userToAdd);


        User addedUser = userService.addUser(userToAdd);

        assertNotNull(addedUser);
        assertEquals("BOB", addedUser.getName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void updateUserTest() {
        UUID userId = UUID.randomUUID();
        User updatedUserData = new User();
        updatedUserData.setName("UpdatedName");
        updatedUserData.setLastName("UpdatedLastName");
        updatedUserData.setBirthYear(1990);
        updatedUserData.setRFC("UpdatedRFC");
        updatedUserData.setJobNationality(JobNationality.valueOf("MX"));

        when(userRepository.findById(eq(userId))).thenReturn(java.util.Optional.of(new User())); // assuming a person with given ID exists
        when(userRepository.save(any(User.class))).thenReturn(updatedUserData);

        User updatedUser = userService.updateUser(userId, updatedUserData);

        assertNotNull(updatedUser);
        assertEquals("UpdatedName", updatedUser.getName());

        verify(userRepository, times(1)).findById(eq(userId));
        verify(userRepository, times(1)).save(any(User.class));
    }
    @Test
    void updateUser_ThrowsNotFoundException() {
        UUID userId = UUID.randomUUID();
        User updatedUser = new User();
        updatedUser.setName("UpdatedName");
        updatedUser.setBirthYear(1990);

        when(userRepository.findById(eq(userId))).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userService.updateUser(userId, updatedUser));

        verify(userRepository, times(1)).findById(eq(userId));

        String expectedErrorMessage = "User not found with ID: " + userId;
        String actualErrorMessage = exception.getMessage();
        assertTrue(actualErrorMessage.contains(expectedErrorMessage), "Expected error message not found");
    }
    @Test
    public void testAddBillToUser() {
        Bill bill = new Bill();
        bill.setOwner("testUser");
        bill.setAmount(BigDecimal.valueOf(100.00));

        User user = new User();
        user.setName("testUser");
        user.setBills(new HashSet<>());

        when(userRepository.findByName("testUser")).thenReturn(user);
        userService.addBillToUser(bill);

        verify(userRepository).save(user);

        assertEquals(1, user.getBills().size());
        assertTrue(user.getBills().contains(bill));
    }
}

