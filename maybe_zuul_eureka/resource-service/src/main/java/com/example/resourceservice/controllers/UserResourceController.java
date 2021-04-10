package com.example.resourceservice.controllers;

import com.example.resourceservice.dtos.UserDetailsDTO;
import com.example.resourceservice.feignService.UserServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserResourceController {

    @Autowired
    private UserServiceFeign userServiceFeign;

    @PostMapping("/saveUpdate")
    public UserDetailsDTO saveUpdate(@RequestBody UserDetailsDTO user) {
        return null;
    }

    @GetMapping("/getById/{id}")
    public UserDetailsDTO getUserById(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/getByName/{name}")
    public UserDetailsDTO getUserByName(@PathVariable String name) {
        return null;
    }
}
