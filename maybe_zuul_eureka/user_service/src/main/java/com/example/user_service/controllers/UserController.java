package com.example.user_service.controllers;

import com.example.user_service.dtos.UserEntityDTO;
import com.example.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    // GET, POST, DELETE, UPDATE (modify)

    @GetMapping("/user/id={id}")
    public UserEntityDTO getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/user/name={name}")
    public UserEntityDTO getUserByName(@PathVariable String name) {
        return userService.getByName(name);
    }

    @PostMapping("/user/register/id={id}/name={name}/password={password}")
    public HttpStatus registerUser(@PathVariable Long id, @PathVariable String name, @PathVariable String password) {
        userService.register(new UserEntityDTO(id, name, password));
        return HttpStatus.OK;
    }

    @PutMapping("/user/modify/new_password={password}")
    public HttpStatus modifyUserPassword(@PathVariable String password) {
        // TO BE IMPLEMENTEDDD LOOOL
        return HttpStatus.OK;
    }
}
