package com.example.user_service.controllers;

import com.example.user_service.dtos.UserEntityDTO;
import com.example.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public List<UserEntityDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/getOne/{id}")
    public Optional<UserEntityDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    public boolean registerUser(@RequestBody UserEntityDTO user) {
        return userService.registerUser(user);
    }

    @DeleteMapping("/deleteAll")
    public boolean deleteAllUsers() {
        return userService.deleteAllUsers();
    }
}
