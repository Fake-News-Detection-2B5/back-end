package com.example.demo.user;

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

    @DeleteMapping(path = "{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping(path = "{id}")
    public boolean updateUser(@PathVariable Long id, @RequestBody CUser user) {
        return userService.updateUser(id, user);
    }
}
