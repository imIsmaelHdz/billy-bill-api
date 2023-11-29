package com.billy.operations.api.service;

import com.billy.operations.api.controller.exception.UserNotFoundException;
import com.billy.operations.api.model.Bill;
import com.billy.operations.api.model.Tax;
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
                .map(userFound -> {
                    userFound.setName(updatedUser.getName());
                    userFound.setEmail(updatedUser.getEmail());
                    userFound.setLastName(updatedUser.getLastName());
                    userFound.setBirthYear(updatedUser.getBirthYear());
                    userFound.setRFC(updatedUser.getRFC());
                    userFound.setProfile(updatedUser.getProfile());
                    userFound.setJobNationality(updatedUser.getJobNationality());

                    return userRepository.save(userFound);
                })
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    @Transactional
    public void deleteUser(UUID id){
        userRepository.deleteById(id);
    }

    public User findByName(String name) {
        Optional<User> optionalPerson = Optional.ofNullable(userRepository.findByName(name));
        return optionalPerson.orElseThrow(() -> new UserNotFoundException("User not found with Name: " + name));
    }

    public User findByRFC(String RFC) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByRFC(RFC));
        return optionalUser.orElseThrow(() -> new UserNotFoundException("User not found with RFC: " + RFC));
    }

    public User findByCustomId(UUID customId) {
        Optional<User> optionalPerson = Optional.ofNullable(userRepository.findByCustomId(customId));
        return optionalPerson.orElseThrow(() -> new UserNotFoundException("User not found with id: " + customId));
    }

    @Transactional
    public void addBillToUser(Bill bill) {
        User user = findByName(bill.getOwner());
        user.getBills().add(bill);
        userRepository.save(user);
    }

    @Transactional
    public void addRegimesToUser(UUID userId,Tax tax) {
        User userFound = findByCustomId(userId);
        userFound.getRegimes().add(tax);
        userRepository.save(userFound);
    }
}
