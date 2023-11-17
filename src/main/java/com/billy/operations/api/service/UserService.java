package com.billy.operations.api.service;

import com.billy.operations.api.controller.exception.UserNotFoundException;
import com.billy.operations.api.model.Bill;
import com.billy.operations.api.model.User;
import com.billy.operations.api.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public User addUser(User user) {return userRepository.save(user);}

    @Transactional
    public User updateUser(UUID id, User updatedUser) {
        return userRepository.findById(id)
                .map(UserFound -> {
                    UserFound.setName(updatedUser.getName());
                    UserFound.setEmail(updatedUser.getEmail());
                    UserFound.setLastName(updatedUser.getLastName());
                    UserFound.setBirthYear(updatedUser.getBirthYear());
                    UserFound.setRFC(updatedUser.getRFC());
                    UserFound.setJobNationality(updatedUser.getJobNationality());
                    return userRepository.save(UserFound);
                })
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    public User findByName(String name) {
        Optional<User> optionalPerson = Optional.ofNullable(userRepository.findByName(name));
        return optionalPerson.orElseThrow(() -> new UserNotFoundException("User not found with Name: " + name));
    }

    public User findByRFC(String RFC) {
        Optional<User> optionalPerson = Optional.ofNullable(userRepository.findByRFC(RFC));
        return optionalPerson.orElseThrow(() -> new UserNotFoundException("User not found with RFC: " + RFC));
    }

    @Transactional
    public void addBillToUser(Bill bill) {
        User user = findByName(bill.getOwner());
        user.getBills().add(bill);
        userRepository.save(user);
    }
}
