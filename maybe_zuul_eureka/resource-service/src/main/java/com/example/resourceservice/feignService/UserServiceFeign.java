package com.example.resourceservice.feignService;

import com.example.resourceservice.dtos.UserEntityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "user-service")
public interface UserServiceFeign {

    @GetMapping("/api/getAll")
    public List<UserEntityDTO> getUsers();

    @GetMapping("/api/getOne/{id}")
    public Optional<UserEntityDTO> getUserById(@PathVariable Long id);

    @PostMapping("/api/register")
    public boolean registerUser(@RequestBody UserEntityDTO user);
}

/*
* GET & POST API Mappings
The rest of all the GET and POST mapping should be as same as API end points available in user-service controller. (refer user-service controller).
* Then the feign client resolve the end point in smart way.

* */
/*
* @GetMapping("/user/id={id}")
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
* */