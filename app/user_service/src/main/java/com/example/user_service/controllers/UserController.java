package com.example.user_service.controllers;

import com.example.user_service.entities.UserEntity;
import com.example.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/private/getAll")
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/private/get/{id}")
    public Optional<UserEntity> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/public/register")
    public boolean registerUser(@RequestBody UserEntity user) {
        return userService.registerUser(user);
    }


    @DeleteMapping("/private/delete/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @DeleteMapping("/private/deleteAll")
    public boolean deleteAllUsers() {
        return userService.deleteAllUsers();
    }

    @PutMapping("/private/update/{id}")
    public boolean updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        return userService.updateUser(id, user);
    }
}
