package com.example.demo;


import com.example.demo.controllers.UserController;
import com.example.demo.entities.CUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MainModule {
    private final UserController userController;
    /*
       Code to be added later on
    private final AIController aiController;
    private final IngestionController ingestionController;
    */



    @Autowired
    public MainModule(UserController userController) {
        this.userController = userController;
    }


    // Intrebari
    // 1. diferenta intre PATH si VALUE (un fel de alias?)
    // 2. daca vrem sa interactionam cu user controller DOAR PRIN Main Module, nu trebuie ca @RestController sa fie doar MainModule,
    // iar UserController sa fie strict o clasa intermediara?
    // 3. Ce alte functionalitati ar mai putea fi adauga la partea de Service sau de controller? Un controller poate avea mai multe service uri?
    // si un service poate avea mai multe repo-uri?
    // zuul filter (port 801 face req pe 800 - anume user controller)
    // service - interc cu baza de date
    // controller - interc cu utilizatorul (trb sa fie scurt)

    /**
     * Method which verifies that a user exists and in the database when he tries to login.
     * @param userToVerify the information about the user
     * @return A HTTP status of 200 (Ok) if the login was successful (the user was found in the database), otherwise a HTTP status of 404 (Not found).
     */
    @GetMapping(value = "login",
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus loginUser(@RequestBody CUser userToVerify) {
        if (userController.getUserById(userToVerify.getId()).isPresent()) {
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    /**
     * Function that gets called when a POST request is made for registering a new user.
     * @param newUser the information associated with the user who wants to register
     * @return a HttpStatus.CREATED if the user was registered or HttpStatus.NOT_ACCEPTABLE if the user was not added.
     */
    @PostMapping(value = "/register",
                 consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CUser> registerUser(@RequestBody CUser newUser) {
        if (userController.registerUser(newUser)) {
            // This may be a better way?
            // return new ResponseEntity<>(HttpStatus.ACCEPTED);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Method which updates an already existing user.
     * @param userToUpdate the user to update (he needs both the password and the username for the update to take place)
     * @param newUserInfo the new information about the user (new username/password)
     * @return HTTP status of 200 (Ok) on success, HTTP status of 404 (Not Found) on fail
     */
    @PutMapping(value = "update-user",
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus updateUser(@RequestBody CUser userToUpdate, @RequestBody CUser newUserInfo) {
        if (userController.updateUser(userToUpdate.getId(), newUserInfo)) {
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    /**
     * Method that is called upon deletion of a user from the database.
     * @param userToDelete the user to be deleted
     * @return HTTP status of 200 (Ok) on success, HTTP status 400 (Bad Request) otherwise
     */
    @DeleteMapping(value = "delete-user",
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus deleteUser(@RequestBody CUser userToDelete) {
        if (userController.deleteUser(userToDelete.getId())) {
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

}
