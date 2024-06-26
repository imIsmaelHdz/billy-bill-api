package com.billy.operations.api.controller;

import com.billy.operations.api.dto.UserDto;

import com.billy.operations.api.dto.mapper.UserMapper;
import com.billy.operations.api.model.User;
import com.billy.operations.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@Tag(name = "Users")
@RequestMapping(value ="/users")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Return all users")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.findAllUsersDto();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userDtos);
    }

    @Operation(summary = "Create a new user")
    @ApiResponse(responseCode = "201", description = "CREATED")
    @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> addPerson(@RequestBody User user) {
        User saved = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(saved);
    }

    @Operation(summary = "Update a user by given a customId")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
        User updated = userService.updateUser(id, updatedUser);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(updated);
    }

    @Operation(summary = "Return a user by give the name")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @GetMapping(value ="/name/{name}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDto> getUserByName(@PathVariable String name) {
        User found =  userService.findByName(name);
        UserDto userDto = UserMapper.INSTANCE.userToUserDto(found);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userDto);
    }

    @Operation(summary = "Return a user by give the custom ID")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @GetMapping(value ="/id/{customId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDto> getUserByCustomId(@PathVariable UUID customId) {
        User found =  userService.findByCustomId(customId);
        UserDto userDto = UserMapper.INSTANCE.userToUserDto(found);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userDto);
    }

}
