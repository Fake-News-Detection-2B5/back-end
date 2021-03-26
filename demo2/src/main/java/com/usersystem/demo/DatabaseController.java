package com.usersystem.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.UUID;

@RestController
public class DatabaseController {


    @GetMapping("/getID/{id}")
    public DatabaseEntry getUserById(@PathVariable UUID id) {
        return Database.getPersonByID(id);
    }

    @GetMapping("/getByName/{username}")
    public DatabaseEntry getUserByUsername(@PathVariable String username) {
        return Database.getPersonByName(username);
    }


    @PostMapping("/register/{username}/{password}")
    public void setUser(@PathVariable String username,@PathVariable String password) {
        Database.addPerson(new DatabaseEntry(username, password));
    }

}
