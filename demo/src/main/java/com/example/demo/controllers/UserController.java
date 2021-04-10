package com.example.demo.controllers;

import com.example.demo.entities.CUser;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<CUser> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "{id}")
    public Optional<CUser> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public boolean registerUser(@RequestBody CUser user) {
        return userService.registerUser(user);
    }

    /**
     * METHOD NEEDS UPDATE: it should return an error code specific to the API (not a HTTP status!)
     * Error codes needed: user_not_found, invalid_password
     *
     * Method that deletes a user from the database.
     * @param id the id of the user that needs to be deleted
     * @return true on success, false otherwise
     */
    @DeleteMapping(path = "{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping(path = "{id}")
    public boolean updateUser(@PathVariable Long id, @RequestBody CUser user) {
        return userService.updateUser(id, user);
    }
}
