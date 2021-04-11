package com.example.resourceservice.controllers;

import com.example.resourceservice.dtos.UserEntityDTO;
import com.example.resourceservice.feignService.UserServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserResourceController {

    @Autowired
    private UserServiceFeign userServiceFeign;

    @GetMapping("/getAll")
    public List<UserEntityDTO> getUsers() {
        return userServiceFeign.getUsers();
    }

    @GetMapping("/getOne/{id}")
    public Optional<UserEntityDTO> getUserById(@PathVariable Long id) {
        return userServiceFeign.getUserById(id);
    }

    @PostMapping("/register")
    public boolean registerUser(@RequestBody UserEntityDTO user) {
        return userServiceFeign.registerUser(user);
    }
}
